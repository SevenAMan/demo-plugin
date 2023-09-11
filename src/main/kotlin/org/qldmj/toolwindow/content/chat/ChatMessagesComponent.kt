package org.qldmj.toolwindow.content.chat

import com.intellij.ui.components.JBLabel
import javax.swing.JPanel

class ChatMessagesComponent(cms: ChatMessage) : JPanel() {
    init {
        val msg = JBLabel(cms.message)
        add(msg)
    }
}