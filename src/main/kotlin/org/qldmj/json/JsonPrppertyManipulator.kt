package org.qldmj.json

import com.intellij.json.psi.JsonElementGenerator
import com.intellij.json.psi.JsonProperty
import com.intellij.json.psi.JsonStringLiteral
import com.intellij.json.psi.impl.JsonPsiImplUtils
import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator

class JsonPropertyManipulator: AbstractElementManipulator<JsonProperty>() {
    override fun handleContentChange(element: JsonProperty, range: TextRange, newContent: String?): JsonProperty? {
        val key = JsonPsiImplUtils.getName(element)
        val value = element.children[1] as JsonStringLiteral
        val originalContent: String = element.getText()
        val withoutQuotes = getRangeInElement(element)
        val generator = JsonElementGenerator(element.getProject())
        val replacement = originalContent.substring(withoutQuotes.startOffset, range.getStartOffset()) + newContent + originalContent.substring(range.getEndOffset(), withoutQuotes.endOffset)
        val ele = generator.createProperty(key, replacement)
        return ele
    }
}