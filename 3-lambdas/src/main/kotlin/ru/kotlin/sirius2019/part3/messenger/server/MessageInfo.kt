package ru.kotlin.sirius2019.part3.messenger.server

import java.time.Instant

/**
 * Сообщение
 */
data class MessageInfo (val messageId: Int, val memberId: Int, var text: String) {
    internal val createdOn = Instant.now()
}
