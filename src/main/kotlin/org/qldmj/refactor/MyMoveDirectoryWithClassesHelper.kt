package org.qldmj.refactor

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.refactoring.listeners.RefactoringElementListener
import com.intellij.refactoring.move.moveClassesOrPackages.MoveDirectoryWithClassesHelper
import com.intellij.usageView.UsageInfo
import com.intellij.util.Function

class MyMoveDirectoryWithClassesHelper : MoveDirectoryWithClassesHelper() {
    override fun findUsages(
        files: MutableCollection<out PsiFile>?,
        dirs: Array<out PsiDirectory>?,
        usages: MutableCollection<in UsageInfo>?,
        p3: Boolean,
        p4: Boolean,
        project: Project?
    ) {
        val b = p3 || p4
    }

    override fun move(
        file: PsiFile?, dir: PsiDirectory?,
        p2: MutableMap<PsiElement, PsiElement>?,
        p3: MutableList<in PsiFile>?,
        p4: RefactoringElementListener?
    ): Boolean {
        val b = file == null
        return false
    }

    override fun postProcessUsages(usages: Array<out UsageInfo>?, function: Function<in PsiDirectory, out PsiDirectory>?) {
        usages?:return
        usages.size
    }

    override fun beforeMove(p0: PsiFile?) {
        TODO("Not yet implemented")
    }

    override fun afterMove(element: PsiElement?) {
        element?:return
        val kClass = element::class
    }
}