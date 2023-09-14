package org.qldmj.toolwindow.content.chat

import com.intellij.ui.JBColor
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.JTextPane
import javax.swing.text.SimpleAttributeSet

class ChatMessagesComponent(cms: ChatMessage) : JPanel() {

    private val msgComponent = JTextPane()
    private val doc = msgComponent.document

    init {
        msgComponent.text = cms.message
        doc.insertString(0, cms.message, SimpleAttributeSet().apply {
        })
        layout = FlowLayout(if (cms.question) FlowLayout.RIGHT else FlowLayout.LEFT)
        add(msgComponent)
        border = BorderFactory.createLineBorder(JBColor(Color.WHITE, Color.darkGray), 2)
    }

    fun updateMsg(msg: String) {
        doc.insertString(doc.length, msg, SimpleAttributeSet())
    }
}