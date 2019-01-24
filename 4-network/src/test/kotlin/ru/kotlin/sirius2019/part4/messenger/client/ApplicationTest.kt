package ru.kotlin.sirius2019.part4.messenger.client

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ru.kotlin.sirius2019.part4.messenger.server.*

class ApplicationTest {

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun testHealth() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/v1/health").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("OK", response.content)
            }
        }
    }

    @Test
    fun testUserCreation() {
        val userData = NewUserInfo("pupkin", "Pupkin", "password")
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Post, "/v1/users") {
                setBody(objectMapper.writeValueAsString(userData))
                addHeader("Content-type", "application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val content = response.content ?: "Empty response received!"
                val user = objectMapper.readValue<HashMap<String,String>>(content)
                assertEquals(userData.userId, user["userId"])
                assertEquals(userData.displayName, user["displayName"])
            }
        }
    }
}
