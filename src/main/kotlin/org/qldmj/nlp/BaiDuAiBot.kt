package org.qldmj.nlp

import com.intellij.openapi.application.ApplicationManager
import com.intellij.util.io.HttpRequests

class BaiDuAiBot {

    fun ask(messageItems: List<MessageItem>, callback: (String) -> Unit) {
        /*        val gson = Gson()
                val token = BaiDuAiToken().getToken()
                HttpRequests.post(
                    "${BAIDUAiKey.BIG.BOT_URL}?access_token=$token",
                    HttpRequests.JSON_CONTENT_TYPE
                ).connect {
                    it.write(gson.toJson(messageItems))
                    val inputStream = it.connection.getInputStream()
                    val byte = ByteArray(1024)
                    var len = 0
                    while (len != -1) {
                        len = inputStream.read(byte)
                        callback(String(byte, 0, len))
                    }
                }*/
        ApplicationManager.getApplication().runWriteAction {
            HttpRequests.request("http://127.0.0.1:8080/chat/test").connect {
                val inputStream = it.connection.getInputStream()
                val byte = ByteArray(512)
                var len = 0
                while (true) {
                    len = inputStream.read(byte)
                    if (len == -1)  {
                        break
                    }
                    val string = String(byte, 0, len)
                    callback(string)
                }
            }
        }
    }
}