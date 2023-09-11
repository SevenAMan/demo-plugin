package org.qldmj.utils

import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager


object CodeFormat {
    /**
     * 代码格式化
     * @param file  文件
     */
    fun formatText(file: PsiFile, startOffset: Int, endOffset: Int) {
        CodeStyleManager.getInstance(file.project)
            .reformatText(file, startOffset, endOffset)
    }
}