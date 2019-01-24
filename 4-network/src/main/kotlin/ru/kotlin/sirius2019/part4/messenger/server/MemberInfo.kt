package ru.kotlin.sirius2019.part4.messenger.server

/**
 * Участник чата
 */
data class MemberInfo(val memberId: Int, val chatId: Int, val chatDisplayName: String, val memberDisplayName: String, val userId: String)