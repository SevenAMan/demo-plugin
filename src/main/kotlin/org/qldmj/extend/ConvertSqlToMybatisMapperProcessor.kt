package org.qldmj.extend

import com.intellij.codeInsight.editorActions.CopyPastePostProcessor
import com.intellij.codeInsight.editorActions.TextBlockTransferableData
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.RangeMarker
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiFile
import com.intellij.testFramework.utils.editor.findVirtualFile
import java.awt.datatransfer.Transferable

class ConvertSqlToMybatisMapperProcessor : CopyPastePostProcessor<TextBlockTransferableData>() {

    override fun collectTransferableData(
        p0: PsiFile,
        p1: Editor,
        p2: IntArray,
        p3: IntArray
    ): MutableList<TextBlockTransferableData> {
        TODO("Not yet implemented")
    }

    override fun extractTransferableData(content: Transferable): MutableList<TextBlockTransferableData> {
        return super.extractTransferableData(content)
    }

    override fun processTransferableData(
        project: Project,
        editor: Editor,
        bounds: RangeMarker,
        caretOffset: Int,
        indented: Ref<in Boolean>,
        values: MutableList<out TextBlockTransferableData>
    ) {
        val document = editor.document
        val file = document.findVirtualFile() ?: return

    }
}