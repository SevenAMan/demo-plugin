package org.qldmj.toolwindow.content.chat

import com.intellij.ui.components.JBLabel
import javax.swing.JPanel

class ChatMessagesComponent(cms: ChatMessage) : JPanel() {

    val msgComponent = JBLabel(cms.message)

    init {
        add(msgComponent)
    }

    fun updateMsg(msg: String) {
        msgComponent.text += msg
    }
}