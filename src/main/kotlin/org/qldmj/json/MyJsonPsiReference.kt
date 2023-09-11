package org.qldmj.json

import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope

class MyJsonPsiReference(private var element: PsiElement, private val clazz: String) : PsiPolyVariantReference, PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? {
        val result = multiResolve(false)

        if (result.isEmpty()) {
            return null
        }
        return result[0].element
    }

    override fun multiResolve(p0: Boolean): Array<ResolveResult> {
        val project = element.project
        val findClass = JavaPsiFacade.getInstance(project)
            .findClass(clazz, GlobalSearchScope.allScope(project)) ?: return emptyArray<ResolveResult>()
        return arrayOf(PsiElementResolveResult(findClass))
    }
}