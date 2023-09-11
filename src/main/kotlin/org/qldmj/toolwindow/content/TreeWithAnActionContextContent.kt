package org.qldmj.toolwindow.content

import com.intellij.ide.ui.customization.CustomActionsSchema
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.PopupHandler
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.treeStructure.Tree
import org.qldmj.toolwindow.MyDataKeys
import java.awt.Component
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class TreeWithAnActionContextContent {

    private val root = DefaultMutableTreeNode("root")
    private val treeModule = DefaultTreeModel(root)
    private val tree = Tree(treeModule)

    init {
        treeModule.insertNodeInto(DefaultMutableTreeNode("child_1"), root, 0)
        treeModule.insertNodeInto(DefaultMutableTreeNode("child_2"), root, 1)
        treeModule.insertNodeInto(DefaultMutableTreeNode("child_3"), root, 2)
        treeModule.insertNodeInto(DefaultMutableTreeNode("child_4"), root, 3)
        tree.isRootVisible = false
        treeModule.reload()
        ApplicationManager.getApplication().invokeLater { installTreePopupMenu() }
        tree.dragEnabled = true
    }
    private fun installTreePopupMenu() {
        val group = CustomActionsSchema.getInstance()
            .getCorrectedAction("qldmj.test.function.action.group") as ActionGroup
        tree.addMouseListener(object : PopupHandler() {
            override fun invokePopup(component: Component?, x: Int, y: Int) {
                val popupMenu = ActionManager.getInstance().createActionPopupMenu("MyCustomTreePopupMenu", group)
                popupMenu.setTargetComponent(tree)
                val treePath = tree.getPathForLocation(x, y)
                val node = treePath?.lastPathComponent
                popupMenu.setDataContext {
                    SimpleDataContext.builder().add(MyDataKeys.myToolModel, treeModule)
                        .add(MyDataKeys.myToolNode, if (node is DefaultMutableTreeNode) node else null)
                        .build()
                }
                popupMenu.component.show(tree, x, y)
            }

        })
    }

    fun getContent(): Content {
        return ContentFactory.getInstance().createContent(ScrollPaneFactory.createScrollPane(tree), "树的上下文菜单", false)
    }
}