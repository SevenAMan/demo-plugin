package org.qldmj.toolwindow.content.chat

import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.BorderFactory
import javax.swing.JPanel

class ChatMessagesComponent(cms: ChatMessage) : JPanel() {

    private val msgComponent = JBLabel(cms.message)

    init {
        layout = FlowLayout(if (cms.question) FlowLayout.RIGHT else FlowLayout.LEFT)
        add(msgComponent)
        border = BorderFactory.createLineBorder(JBColor(Color.WHITE, Color.darkGray), 2)
    }

    fun updateMsg(msg: String) {
        msgComponent.text += msg
    }
}