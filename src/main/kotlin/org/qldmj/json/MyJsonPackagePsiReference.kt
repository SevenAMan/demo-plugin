package org.qldmj.json

import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.containers.toArray

class MyJsonPackagePsiReference(
    private var element: PsiElement,
    private val clazz: String
) :
    PsiPolyVariantReference, PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? {
        val result = multiResolve(false)

        if (result.isEmpty()) {
            return null
        }
        return result[0].element
    }

    override fun multiResolve(p0: Boolean): Array<ResolveResult> {
        val project = element.project
        val psiClass = JavaPsiFacade.getInstance(project)
            .findClass(clazz, GlobalSearchScope.allScope(project)) ?: return emptyArray<ResolveResult>()
        val file = psiClass.containingFile
        val directory = file.containingDirectory
        val packages = mutableListOf<PsiPackage>()
        var currentDirectory: PsiDirectory? = directory
        while (currentDirectory != null) {
            val psiPackage = JavaDirectoryService.getInstance().getPackage(currentDirectory)
            if (psiPackage != null) {
                packages.add(psiPackage)
            }
            currentDirectory = currentDirectory.parentDirectory
        }

        return packages.map { PsiElementResolveResult(it) }.toArray(emptyArray())
    }

}