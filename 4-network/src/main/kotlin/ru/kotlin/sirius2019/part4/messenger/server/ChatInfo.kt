package ru.kotlin.sirius2019.part4.messenger.server

/**
 * Чат
 */
data class ChatInfo(val chatId: Int, val defaultName: String)

data class NewChatInfo(val defaultName: String)
data class JoinChatInfo(val defaultName: String, val secret: String)
data class InviteChatInfo(val userId: String)

