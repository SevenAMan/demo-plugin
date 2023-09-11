package org.qldmj.refactor

import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFileHandler
import com.intellij.usageView.UsageInfo

class MyMoveFileHandler: MoveFileHandler() {
    override fun canProcessElement(p0: PsiFile?): Boolean {
        TODO("Not yet implemented")
    }

    override fun prepareMovedFile(p0: PsiFile?, p1: PsiDirectory?, p2: MutableMap<PsiElement, PsiElement>?) {
        TODO("Not yet implemented")
    }

    override fun findUsages(p0: PsiFile?, p1: PsiDirectory?, p2: Boolean, p3: Boolean): MutableList<UsageInfo>? {
        TODO("Not yet implemented")
    }

    override fun retargetUsages(p0: MutableList<UsageInfo>?, p1: MutableMap<PsiElement, PsiElement>?) {
        TODO("Not yet implemented")
    }

    override fun updateMovedFile(p0: PsiFile?) {
        TODO("Not yet implemented")
    }
}