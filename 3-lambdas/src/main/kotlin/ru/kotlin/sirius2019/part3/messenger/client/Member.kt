package ru.kotlin.sirius2019.part3.messenger.client

/**
 * Участник чата
 */
class Member(val memberId: Int, val displayName: String, val memberUserId: String, chat: Chat) : ChatAware(chat)