package org.qldmj.action.test

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.ui.TextFieldWithAutoCompletion
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JPopupMenu


class TestTextFieldWithAutoCompletionAction : AnAction("Test TextFieldWithAutoCompletion") {
    override fun actionPerformed(p0: AnActionEvent) {
        val project = p0.project ?: return
        DialogBuilder(project).apply {
            val text = TextFieldWithAutoCompletion.create(project,
                arrayListOf("ab", "cd", "ef", "ok"),
                false,
                ""
            )
            text.setPreferredWidth(100)
            val panel = JPanel()
            panel.add(text)
            val button = JButton("Test")
            button.addActionListener {
                val popupMenu = JPopupMenu()
                popupMenu.add(text)
                popupMenu.show(button, 0, button.height)
            }
            panel.add(button)
            this.centerPanel(panel).title("TextFieldWithAutoCompletion")
        }.show()
    }
}