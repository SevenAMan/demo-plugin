package org.qldmj.action.test

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.util.asSafely
import com.intellij.util.io.HttpRequests
import com.intellij.util.io.readCharSequence
import io.ktor.utils.io.*
import org.jetbrains.kotlin.tooling.core.withClosure
import org.jetbrains.kotlin.utils.addToStdlib.assertedCast

class TestHttpChatAction : AnAction("Test Http Chat Action") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        DialogBuilder(project).apply {
            addOkAction()
            setOkOperation {
                HttpRequests.request("http://localhost:8080/chat/test").connect {
                    val inputStream = it.connection.getInputStream()

                    val byte = ByteArray(1024)
                    var len = 0
                    while (len != -1) {
                        len = inputStream.read(byte)
                        println(String(byte, 0, len))
                    }

                }
            }
        }.show()
    }
}

/*
*     @GetMapping("/chat/test")
    fun getMessages(response: HttpServletResponse) {
        val msg = "这个是一个ai的chat文本！"
        response.characterEncoding = "UTF-8"
        response.contentType = "text/plain"
        for (c in msg) {
            response.writer.write(charArrayOf(c))
            response.flushBuffer()
            Thread.sleep(200)
        }
    }
* */