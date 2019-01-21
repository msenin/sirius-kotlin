package ru.kotlin.sirius2019.part3.messenger.server

/**
 * Пользователь
 */
data class UserInfo(val userId: String, val displayName: String, internal val passwordHash: String)