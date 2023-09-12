package org.qldmj.toolwindow.content.chat

import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBUI
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.BorderFactory
import javax.swing.JPanel

class QAItemPanel(private val cms: ChatMessage) : JPanel(GridBagLayout()) {

    private val INSETS = JBUI.insets(3, 10)
    private val icon = if (cms.question) ChatIcon.questionerIcon() else ChatIcon.answererIcon()
    private val emptyIcon = JBLabel()

    private val messagesComponent = ChatMessagesComponent(cms)

    init {
        emptyIcon.size = icon.size
        border = BorderFactory.createLineBorder(JBColor(Color.WHITE, Color.darkGray))
        create()
    }

    private fun create() {
        add(emptyIcon, GridBagConstraints().apply {
            gridx = if (cms.question) 0 else 2
            insets = INSETS
            anchor = GridBagConstraints.NORTHWEST
        })
        add(messagesComponent, GridBagConstraints().apply {
            gridx = 1
            insets = INSETS
            fill = GridBagConstraints.BOTH
            anchor = GridBagConstraints.NORTHWEST
        })
        add(icon, GridBagConstraints().apply {
            gridx = if (cms.question) 2 else 0
            insets = INSETS
            anchor = GridBagConstraints.NORTHWEST
        })
    }

    fun updateAnswer(msg: String) {
        messagesComponent.updateMsg(msg)
    }
}