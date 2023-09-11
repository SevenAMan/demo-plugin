package org.qldmj.toolwindow.content.chat

import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class ChatContent : JPanel(GridBagLayout()) {
    init {
        repeat(5) {
            val gridBag: GridBagConstraints.() -> Unit = {
                gridx = 0
                fill = GridBagConstraints.BOTH
                anchor = GridBagConstraints.NORTH
            }
            add(QAItemPanel(ChatMessage(true, "问题问了")), GridBagConstraints().apply(gridBag))
            add(QAItemPanel(ChatMessage(false, "回答回了")), GridBagConstraints().apply(gridBag))

        }
    }

    fun getContent(): Content {
        return ContentFactory.getInstance().createContent(ScrollPaneFactory.createScrollPane(this), "Chat", false)
    }
}