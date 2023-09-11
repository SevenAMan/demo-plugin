package org.qldmj.toolwindow

import com.intellij.openapi.actionSystem.DataKey
import kotlinx.coroutines.runBlocking
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

object MyDataKeys {

    private const val MY_TOOL_TREE_MODEL = "myToolTreeModel"
    private const val MY_TOOL_TREE_NODE = "myToolTreeNode"

    val myToolModel = DataKey.create<DefaultTreeModel>(MY_TOOL_TREE_MODEL)
    val myToolNode = DataKey.create<DefaultMutableTreeNode>(MY_TOOL_TREE_NODE)


}
