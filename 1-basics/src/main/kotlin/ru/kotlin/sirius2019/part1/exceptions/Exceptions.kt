package ru.kotlin.sirius2019.part1.exceptions

import java.lang.ArithmeticException
import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    try {
        while (true) {
            try {
                println("Пожалуйста, введите два числа, разделённых пробелом:")
                processNumbers(scanner.nextDouble(), scanner.nextDouble())
                return
            } catch (e: Exception) {
                println("Ошибка! $e \nПопробуйте снова!")
                scanner.nextLine()
            }
        }
    }
    finally {
        scanner.close()
    }
}

private fun processNumbers(a: Double, b: Double) {
    if (b == 0.0) {
        throw ArithmeticException("Деление на 0")
    }
    println("Результат деления $a на $b: ${ a / b }")
}