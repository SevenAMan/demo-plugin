package org.qldmj.toolwindow.content.chat

import com.intellij.openapi.components.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Service(value = [Service.Level.PROJECT])
class CoroutineScope {
    val scope = CoroutineScope(Dispatchers.IO)
}