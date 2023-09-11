package org.qldmj.action

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler

/**
 * 键入 在编辑器中键入 tab后触发该事件
 */
class MyEditorKeyTabActon : EditorWriteActionHandler() {

    override fun executeWriteAction(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        super.executeWriteAction(editor, caret, dataContext)
    }
}