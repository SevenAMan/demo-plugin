package org.qldmj.action.test

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.ui.components.JBLabel
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.components.BorderLayoutPanel
import kotlinx.coroutines.*

class TestLoadAnAction : AnAction("Test Load An Action") {
    override fun actionPerformed(event: AnActionEvent) {
        runBlocking {
            val animatedIcon = AnimatedIcon.Default.INSTANCE
            val project = event.project ?: return@runBlocking
            val icon = JBLabel()
            val field = JBTextField()
            val panel = BorderLayoutPanel()
            panel.addToCenter(icon)
            panel.addToBottom(field)
            DialogBuilder(project).title(event.presentation.text).centerPanel(panel).apply {
                setOkOperation {
                    icon.icon = animatedIcon
                    launch {
                        withContext(Dispatchers.IO) {
                            delay(5000)
                            icon.icon = null
                            field.text = "over!"
                        }
                    }
                }
            }.show()
        }
    }
}