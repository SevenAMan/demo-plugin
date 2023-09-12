package org.qldmj.toolwindow.content.chat

import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import java.awt.Color
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JPanel

class QAItemPanel(private val cms: ChatMessage) : JPanel(GridLayoutManager(1, 3)) {

    private val icon = if (cms.question) ChatIcon.questionerIcon() else ChatIcon.answererIcon()
    private val emptyIcon = JBLabel()

    private val messagesComponent = ChatMessagesComponent(cms)

    init {
        emptyIcon.size = icon.size
        border = BorderFactory.createLineBorder(JBColor(Color.WHITE, Color.darkGray), 1)
        create()
    }

    private fun create() {
        val nonSize = Dimension(-1, -1)
        add(
            if (cms.question) emptyIcon else icon,
            GridConstraints(
                0,
                0,
                1,
                1,
                GridConstraints.ANCHOR_NORTHWEST,
                GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                nonSize,
                nonSize,
                nonSize
            )
        )
        add(
            messagesComponent, GridConstraints(
                0,
                1,
                1,
                1,
                if (cms.question) GridConstraints.ANCHOR_NORTHWEST else GridConstraints.ANCHOR_NORTHEAST,
                GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_WANT_GROW or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_WANT_GROW or GridConstraints.SIZEPOLICY_CAN_GROW,
                nonSize,
                nonSize,
                nonSize
            )
        )
        add(
            if (!cms.question) emptyIcon else icon, GridConstraints(
                0,
                2,
                1,
                1,
                GridConstraints.ANCHOR_NORTHEAST,
                GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                nonSize,
                nonSize,
                nonSize
            )
        )


    }

    fun updateAnswer(msg: String) {
        messagesComponent.updateMsg(msg)
    }
}