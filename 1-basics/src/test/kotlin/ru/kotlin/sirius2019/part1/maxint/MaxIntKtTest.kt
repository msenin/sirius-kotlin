package ru.kotlin.sirius2019.part1.maxint

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.NumberFormatException

class MaxIntKtTest {

    @Test
    fun testMaxInt() {
        assertMaxInt(arrayOf("1", "2"), "2")
        assertMaxInt(arrayOf("2", "1"), "2")
        assertMaxInt(arrayOf("200", "2"), "200")
        assertMaxInt(arrayOf("-1", "-2"), "-1")
        assertThrows(NumberFormatException::class.java) {
            main(arrayOf("one", "2"))
        }
        assertMaxInt(arrayOf("1"), "Неверное число аргументов")
        assertMaxInt(arrayOf("1", "2", "3"), "Неверное число аргументов")
    }

    private fun assertMaxInt(input: Array<String>, expected: String) {
        val result = catchStdOut {
            main(input)
        }
        assertEquals("$expected$newLine", result)
    }

    private val newLine : String = catchStdOut { println() }

    private fun catchStdOut(block: () -> Unit): String {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        try {
            System.setOut(printStream)
            block()
        }
        finally {
            System.setOut(System.out)
        }
        return outputStream.toString()
    }
}
