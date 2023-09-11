package org.qldmj.action.test

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import org.qldmj.runconfiguration.RunConfigurationUtils
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class TestRemoteRunConfigurationAction : AnAction("Test Remote Run Configuration Action") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return

        val modules = ModuleManager.getInstance(project).modules

        val moduleBox = ComboBox(modules)
        val nameField = JBTextField(20)
        val hostField = JBTextField(20)
        val portField = JBTextField(20)

        moduleBox.renderer = object : DefaultListCellRenderer() {
            override fun getListCellRendererComponent(
                list: JList<*>?,
                value: Any?,
                index: Int,
                isSelected: Boolean,
                cellHasFocus: Boolean
            ): Component {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                if (value is Module) {
                    text = value.name
                    icon = AllIcons.Nodes.Module
                }
                return this
            }
        }

        DialogBuilder().title(event.presentation.text)
            .centerPanel(
                FormBuilder.createFormBuilder()
                    .addLabeledComponent("Module:", moduleBox)
                    .addLabeledComponent("Name:", nameField)
                    .addLabeledComponent("Host:", hostField)
                    .addLabeledComponent("Port:", portField).panel
            )
            .apply {
                addOkAction()
                addCancelAction()
                setOkOperation {
                    dispose()
                    dialogWrapper.close(DialogWrapper.OK_EXIT_CODE)
                    RunConfigurationUtils.addRemoteRunConfiguration(
                        moduleBox.item,
                        nameField.text,
                        hostField.text,
                        portField.text
                    )
                }
            }.show()
    }
}