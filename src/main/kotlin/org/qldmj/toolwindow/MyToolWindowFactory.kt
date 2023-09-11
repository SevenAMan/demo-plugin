package org.qldmj.toolwindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.qldmj.toolwindow.content.chat.ChatContent

class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val manager = toolWindow.contentManager
        manager.addContent(
//            TreeWithAnActionContextContent().getContent()
            ChatContent().getContent()
        )
    }

}