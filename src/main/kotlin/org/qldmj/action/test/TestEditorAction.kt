package org.qldmj.action.test

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task.Backgroundable
import com.intellij.openapi.ui.Messages

class TestEditorAction : AnAction("Test Editor Delay Action") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return

        val task = object : Backgroundable(project, "Test action", true) {
            override fun run(process: ProgressIndicator) {
                Thread.sleep(5000)
                ApplicationManager.getApplication().invokeLater {
                    Messages.showMessageDialog("Over!", "Task", null)
                }
            }

        }
        task.queue()
        ProgressManager.getInstance().run(task)
    }

}