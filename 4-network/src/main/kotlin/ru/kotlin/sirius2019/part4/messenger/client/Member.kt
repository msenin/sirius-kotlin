package ru.kotlin.sirius2019.part4.messenger.client

/**
 * Участник чата
 */
class Member(val memberId: Int, val displayName: String, val memberUserId: String, chat: Chat) : ChatAware(chat)