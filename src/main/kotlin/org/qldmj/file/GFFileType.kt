package org.qldmj.file

import com.intellij.json.JsonFileType

class GFFileType: JsonFileType() {

    companion object {
        val INSTANCE = GFFileType()
    }

    override fun getDefaultExtension() = "gf"

    override fun getName() = "GF"
}