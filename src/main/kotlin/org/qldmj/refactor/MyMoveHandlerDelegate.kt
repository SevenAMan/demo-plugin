package org.qldmj.refactor

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.refactoring.move.MoveHandlerDelegate

class MyMoveHandlerDelegate : MoveHandlerDelegate() {


    override fun canMove(elements: Array<out PsiElement>?, targetContainer: PsiElement?, reference: PsiReference?): Boolean {
        return super.canMove(elements, targetContainer, reference)
    }
}