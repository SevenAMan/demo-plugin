package org.qldmj.nlp

import com.google.gson.Gson
import com.intellij.util.io.HttpRequests
import java.util.Objects

class BaiDuAiToken : AiToken {
    override fun getToken(): String {
        val messages = HttpRequests.post(BAIDUAiKey.BIG.TOKEN_URL, "application/x-www-form-urlencoded")
            .connect {
                it.write("grant_type=client_credentials&client_id=${BAIDUAiKey.BIG.API_KEY}&client_secret=${BAIDUAiKey.BIG.SECRET_KEY}")
                it.readString()
            }
        val map = Gson().fromJson(messages, Map::class.java)
        return Objects.toString(map.getOrDefault("access_token", ""))
    }
}