package org.qldmj.toolwindow.content.chat

import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBUI
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class QAItemPanel(private val cms: ChatMessage) : JPanel(GridBagLayout()) {

    private val INSETS = JBUI.insets(3, 10)
    private val icon = if (cms.question) ChatIcon.questionerIcon() else ChatIcon.answererIcon()
    private val emptyIcon = JBLabel()

    init {
        emptyIcon.size = icon.size
        create()
    }

    private fun create() {
        val messagesComponent = ChatMessagesComponent(cms)
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
}