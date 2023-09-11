package org.qldmj.action.refactor

import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.psi.PsiElement
import com.intellij.refactoring.RefactoringActionHandler
import com.intellij.refactoring.actions.BaseRefactoringAction

class CustomRefactorHandlerAction : BaseRefactoringAction() {
    override fun isAvailableInEditorOnly() = false

    override fun isEnabledOnElements(elements: Array<out PsiElement>): Boolean {
        return true
    }

    override fun getHandler(p0: DataContext): RefactoringActionHandler? {
        return null
    }

    override fun isAvailableForLanguage(language: Language?): Boolean {
        return super.isAvailableForLanguage(language)
    }
}