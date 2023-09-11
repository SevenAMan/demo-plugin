package org.qldmj.json

import com.intellij.json.psi.JsonProperty
import com.intellij.json.psi.JsonStringLiteral
import com.intellij.json.psi.impl.JsonPsiImplUtils
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import org.jetbrains.kotlin.utils.addToStdlib.ifTrue

class JsonPsiReferenceContribute : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        val value = object : PsiReferenceProvider() {
            override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                if (element is JsonStringLiteral && element.parent is JsonProperty) {
                    val name = JsonPsiImplUtils.getName(element.parent as JsonProperty)
                    (name == "clazz").ifTrue {
                        return arrayOf(
                            MyJsonPsiReference(element, element.value),
                            MyJsonPackagePsiReference(element, element.value)
                        )
                    }
                }
                return emptyArray()
            }

        }
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(JsonStringLiteral::class.java), value)
    }

    private fun packages(clazz: String): List<String> {
        if (!(clazz.contains("."))) {
            return emptyList()
        }
        val packages = mutableListOf("")
        val lastPackage = clazz.substring(0, clazz.lastIndexOf(".") - 1)
        packages.add(lastPackage)
        packages.addAll(packages(lastPackage))
        return packages
    }
}