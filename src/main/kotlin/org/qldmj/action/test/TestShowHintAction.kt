package org.qldmj.action.test

import com.intellij.codeInsight.hint.HintManager
import com.intellij.codeInsight.hint.HintManagerImpl
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.ui.LightweightHint
import com.intellij.ui.awt.RelativePoint
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.Point

class TestShowHintAction : AnAction("Show Hint") {
    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = event.dataContext
        val editor = dataContext.getData(LangDataKeys.EDITOR) ?: return
        val panel = FormBuilder.createFormBuilder().addLabeledComponent("Text: ", JBLabel("Text hint")).panel
        /*        HintManagerImpl.getInstanceImpl().showEditorHint(
                    LightweightHint(panel),
                    editor,
                    Point(600, 200),
                    HintManager.HIDE_BY_ANY_KEY or HintManager.UPDATE_BY_SCROLLING,
                    1000,
                    false
                )*/
//        val relativePoint = RelativePoint.getSouthWestOf(editor.component)
//        HintManagerImpl.getInstanceImpl().showHint(panel, relativePoint, HintManager.HIDE_BY_CARET_MOVE, 1000)
    }
}