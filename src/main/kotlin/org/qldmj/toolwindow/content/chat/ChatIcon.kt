package org.qldmj.toolwindow.content.chat

import com.intellij.ui.IconManager
import com.intellij.ui.components.JBLabel
import javax.swing.Icon


object ChatIcon {
    private fun findSvgIcon(path: String): Icon {
        return IconManager.getInstance().getIcon(path, this::class.java.classLoader)
    }

    private fun chatIcon(icon: Icon?, questionIcon: Boolean = true): JBLabel {
        var labelIcon = icon
        if (labelIcon == null) {
            labelIcon = findSvgIcon(if (questionIcon) "icons/chat/question.svg" else "icons/chat/answer.svg")
        }
        return JBLabel(labelIcon)
    }

    fun questionerIcon(icon: Icon? = null) = chatIcon(icon)

    fun answererIcon(icon: Icon? = null) = chatIcon(icon, false)
}
