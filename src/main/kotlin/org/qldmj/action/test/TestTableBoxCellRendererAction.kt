package org.qldmj.action.test

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.Messages
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.components.BorderLayoutPanel
import java.util.*
import javax.swing.DefaultCellEditor
import javax.swing.event.TableModelEvent
import javax.swing.table.DefaultTableModel

class TestTableBoxCellRendererAction : AnAction("Test Table Cell Renderer Action") {

    override fun actionPerformed(event: AnActionEvent) {

        val project = event.project ?: return

        val clickInfoLabel = JBLabel("Empty!")
        val tableModel = DefaultTableModel(emptyArray(), arrayOf("name", "sex"))
        val table = JBTable(tableModel)

        val column = table.columnModel.getColumn(1)
        column.setCellRenderer { _, value, _, _, _, _ ->
            val box = JBCheckBox()
            if (value is Boolean) {
                box.isSelected = value
            }
            box
        }
        column.cellEditor = object : DefaultCellEditor(JBCheckBox()) {}

        tableModel.insertRow(0, arrayOf("zs", true))
        tableModel.insertRow(1, arrayOf("akl", false))

        table.model.addTableModelListener {
            if (it.type == TableModelEvent.UPDATE) {
                val row = it.lastRow
                val col = it.column
                clickInfoLabel.text = Objects.toString(table.getValueAt(row, col), "")
            }
        }

        DialogBuilder(project).title("Test")
            .centerPanel(BorderLayoutPanel().addToCenter(table).addToTop(clickInfoLabel))
            .apply {
                setOkOperation {
                    var string = ""
                    for (i in 0 until table.rowCount) {
                        string += table.getValueAt(i, 0).toString() + ": "
                        string += table.getValueAt(i, 1).toString() + "\n"
                    }
                    Messages.showMessageDialog(string, "Table Info", null)
                }
            }.show()

    }

}