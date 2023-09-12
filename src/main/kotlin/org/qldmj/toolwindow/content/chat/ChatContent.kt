package org.qldmj.toolwindow.content.chat

import com.intellij.icons.AllIcons
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.addExtension
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.fields.ExtendableTextField
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import org.qldmj.nlp.BaiDuAiBot
import org.qldmj.nlp.MessageItem
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JPanel
import java.awt.Dimension


class ChatContent(private val project: Project) : JPanel(BorderLayout()) {

    private val questionTextField = ExtendableTextField()
    private val nonPref = Dimension(-1, -1)
    private val messagesHistoryPanel = JPanel(FlowLayout(FlowLayout.LEFT))

    init {
        messagesHistoryPanel.layout = BoxLayout(messagesHistoryPanel, BoxLayout.Y_AXIS)
        val panel = JPanel(GridLayoutManager(2, 1))
        panel.add(
            messagesHistoryPanel, GridConstraints(
                0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW or GridConstraints.SIZEPOLICY_CAN_GROW, 0,
                nonPref, nonPref, nonPref
            )
        )
        panel.add(
            JBLabel(), GridConstraints(
                1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                0, GridConstraints.SIZEPOLICY_WANT_GROW or GridConstraints.SIZEPOLICY_CAN_GROW,
                nonPref, nonPref, nonPref
            )
        )
        add(panel, BorderLayout.CENTER)
        add(questionTextField, BorderLayout.SOUTH)
        questionTextField.addExtension(AllIcons.Actions.Search, AllIcons.Actions.Search) {
            val text = questionTextField.text
            messagesHistoryPanel.add(QAItemPanel(ChatMessage(true, text)))
            val answer = QAItemPanel(ChatMessage(false, ""))
            messagesHistoryPanel.add(answer)
            object : Task.Backgroundable(project, "正在生成答案") {
                override fun run(p0: ProgressIndicator) {
                    BaiDuAiBot().ask(listOf(MessageItem("user", text))) {
                        answer.updateAnswer(it)
                    }
                }
            }.queue()
        }
    }

    fun getContent(): Content {
        return ContentFactory.getInstance().createContent(ScrollPaneFactory.createScrollPane(this), "Chat", false)
    }
}