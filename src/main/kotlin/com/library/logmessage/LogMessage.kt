package com.library.logmessage

/**
 * This is a log library.
 * Use as described below.
 *
 * Make a message class like "InfoMessages"
 *
 * Coding image
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * InfoMessages.TEST_MESSAGE.log(
 *         "ffirst-message" to "first",
 *         "second-mesasge" to "second"
 * ) { k, v -> log.info(k + v) }
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * Output Log Image
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * [WhaleService_I_0001] i am test message  first-message=first,second-mesasge=second
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

interface LogMessage {
    val messageId: String
    val message: String
}

fun LogMessage.log(vararg p: Pair<String, String>, block: (String, String) -> Unit) {
    block.invoke("[${this.messageId}] ${this.message} ", genMessage(*p))
}

fun genMessage(vararg p: Pair<String, String>): String {
    var message = " "
    p.forEach { element -> message += "${element.first}=${element.second}" + "," }
    return message.removeSuffix(",")
}
