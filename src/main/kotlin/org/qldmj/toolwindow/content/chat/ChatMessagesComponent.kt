package org.qldmj.toolwindow.content.chat

import com.intellij.ui.JBColor
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import java.awt.Color
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.text.SimpleAttributeSet

class ChatMessagesComponent(cms: ChatMessage) : JPanel(GridLayoutManager(1, 1)) {

    private val msgComponent = JTextArea()
    private val doc = msgComponent.document

    init {
        msgComponent.text = cms.message
        msgComponent.lineWrap = true
        msgComponent.isEditable = false
        val nonPre = Dimension(-1, -1)
        add(
            msgComponent, GridConstraints(
                0, 0, 1, 1, if (cms.question) GridConstraints.ANCHOR_EAST else GridConstraints.ANCHOR_WEST,
                GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_WANT_GROW or GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
                nonPre, nonPre, nonPre
            )
        )
        border = BorderFactory.createLineBorder(JBColor(Color.WHITE, Color.darkGray), 2)
    }

    fun updateMsg(msg: String) {
        doc.insertString(doc.length, msg, SimpleAttributeSet())
    }
}