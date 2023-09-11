package org.qldmj.refactor

import com.intellij.json.JsonFileType
import com.intellij.json.psi.JsonElementGenerator
import com.intellij.json.psi.JsonFile
import com.intellij.json.psi.JsonStringLiteral
import com.intellij.json.psi.impl.JsonPsiImplUtils
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiPackage
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.refactoring.listeners.RefactoringElementListener
import com.intellij.refactoring.rename.RenamePsiElementProcessor
import com.intellij.usageView.UsageInfo
import com.intellij.util.containers.toArray
import org.qldmj.json.MyJsonPackagePsiReference
import org.qldmj.json.MyJsonPsiReference

class MyRenamePsiElementProcessor : RenamePsiElementProcessor() {
    override fun canProcessElement(element: PsiElement): Boolean {
        val project = element.project
        if (element is PsiClass || element is PsiPackage) {
            val files = FileTypeIndex.getFiles(JsonFileType.INSTANCE, GlobalSearchScope.allScope(project))
            if (files.isEmpty()) {
                return false
            }
            return files.any {
                return PsiManager.getInstance(project).findFile(it) is JsonFile
            }
        }
        return false
    }

    override fun renameElement(element: PsiElement, newName: String, usages: Array<out UsageInfo>, listener: RefactoringElementListener?) {
        val uad = mutableListOf<UsageInfo>()
        val isClass = element is PsiClass
        val isPackage = element is PsiPackage
        if (isClass || isPackage) {
            val qualifiedName = if (isClass) (element as PsiClass).qualifiedName else (element as PsiPackage).qualifiedName
            val name = if (isClass) (element as PsiClass).name else (element as PsiPackage).name
            if (qualifiedName != null && name != null) {
                val longName = qualifiedName.substring(0, qualifiedName.lastIndexOf(name)) + newName
                usages.forEach {
                    if (it.reference is MyJsonPsiReference || it.reference is MyJsonPackagePsiReference) {
                        val jsonElement = it.element
                        if (jsonElement is JsonStringLiteral) {
                            val value = JsonPsiImplUtils.getValue(jsonElement)
                            val suffix = if (isPackage && value.contains(qualifiedName)) value.substring(qualifiedName.length) else ""
                            jsonElement.replace(JsonElementGenerator(element.project).createStringLiteral(longName + suffix))
                            uad.add(it)
                        }
                    }
                }
            }
        }
        super.renameElement(element, newName, usages.filter { !uad.contains(it) }.toArray(UsageInfo.EMPTY_ARRAY), listener)
    }
}