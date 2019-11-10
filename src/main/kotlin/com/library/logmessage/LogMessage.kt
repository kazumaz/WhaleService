package com.library.logmessage

interface LogMessage {
    val messageId: String
    val message: String
}

fun LogMessage.log(vararg p: Pair<String, String>, block: (String, String) -> Unit) {
    block.invoke("[${this.messageId}] ${this.message} ", genMessage(*p))
}

fun genMessage(vararg p: Pair<String, String>): String {
    var message: String = " "
    p.forEach { element -> message += "${element.first}=${element.second}" + "," }
    return message.removeSuffix(",")
}
