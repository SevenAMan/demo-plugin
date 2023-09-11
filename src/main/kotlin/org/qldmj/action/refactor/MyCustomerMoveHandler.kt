package org.qldmj.action.refactor

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.refactoring.move.MoveHandlerDelegate

class MyCustomerMoveHandler: MoveHandlerDelegate() {
    override fun canMove(dataContext: DataContext?): Boolean {
        return super.canMove(dataContext)
    }



}