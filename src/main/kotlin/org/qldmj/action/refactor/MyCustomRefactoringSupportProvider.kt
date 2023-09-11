package org.qldmj.action.refactor

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement

class MyCustomRefactoringSupportProvider: RefactoringSupportProvider() {

    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {

        return super.isMemberInplaceRenameAvailable(element, context)
    }

    override fun isAvailable(context: PsiElement): Boolean {
        return super.isAvailable(context)
    }




}