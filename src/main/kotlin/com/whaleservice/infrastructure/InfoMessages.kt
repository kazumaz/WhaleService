package com.whaleservice.infrastructure

import com.library.logmessage.LogMessage

enum class InfoMessages (
        override val messageId: String,
        override val message: String
) : LogMessage {
    TEST_MESSAGE(
            "WhaleService_I_0001",
            "i am test message"
    )
}