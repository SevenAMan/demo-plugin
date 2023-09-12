package org.qldmj.nlp

import com.intellij.util.io.HttpRequests

class ChatGPT {
    private val model =  "gpt-3.5-turbo"
    private val url = "https://api.openai.com/v1/chat/completions"
    private val key = "Authorization"

    fun askFromChat() {
      HttpRequests.request(url, )
    }
}