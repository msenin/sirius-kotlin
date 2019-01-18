package ru.kotlin.sirius2019.part2.classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KType
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.javaType

class ClassesTest {

    @Test
    fun testIntPair1() {
        val clazz = IntPair1::class
        assertTrue(clazz.isData, "Класс должен содержать неявные методы hashCode(), equals(), toString(), component1(), component2() и copy()")
        assertTrue(clazz.constructors.size >= 2, "Должно быть как минимум два конструктора")
        assertTrue(clazz.constructors.any { it.parameters.isEmpty() }, "Должно быть конструктор без параметров")
        assertTrue(clazz.constructors.any { it.parameters.size == 2 && isInt(it.parameters[0].type) && isInt(it.parameters[1].type)},"Должно быть конструктор (Int, Int)")
        assertEquals(2, clazz.memberProperties.size, "Должно быть два свойства!")
        assertTrue(clazz.memberProperties.any { it !is KMutableProperty<*> && it.name == "valueX" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueX : Int")
        assertTrue(clazz.memberProperties.any { it !is KMutableProperty<*> && it.name == "valueY" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueY : Int")
        val check = IntPair1()
        assertEquals("IntPair1(valueX=-1, valueY=-1)", check.toString(), "Неверная инициализация в конструкторе без параметров")
    }

    @Test
    fun testIntPair2() {
        val clazz = IntPair2::class
        assertTrue(clazz.isOpen || clazz.isAbstract, "Класс должен допускать наследование")
        assertTrue(clazz.constructors.size >= 2, "Должно быть как минимум два конструктора")
        assertTrue(clazz.constructors.any { it.parameters.size == 2 && isInt(it.parameters[0].type) && isInt(it.parameters[1].type)},"Должно быть конструктор (Int, Int)")
        assertTrue(clazz.constructors.any { it.parameters.isEmpty() && "PRIVATE" == it.visibility?.name }, "Должно быть конструктор без параметров")
        assertEquals(2, clazz.memberProperties.size, "Должно быть два свойства!")
        assertTrue(clazz.memberProperties.any { it is KMutableProperty<*> && it.name == "valueX" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueX : Int")
        assertTrue(clazz.memberProperties.any { it is KMutableProperty<*> && it.name == "valueY" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueY : Int")
        assertTrue(clazz.isAbstract, "Класс должен быть абстрактным, т.к. должен содержать абстрактный метод gcd")
        assertTrue(clazz.memberFunctions.any { it.name == "sum" && !it.isAbstract && it.isOpen && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода sum" )
        assertTrue(clazz.memberFunctions.any { it.name == "prod" && !it.isAbstract && !it.isOpen && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода prod" )
        assertTrue(clazz.memberFunctions.any { it.name == "gcd" && it.isAbstract && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода gcd" )
    }

    @Test
    fun testDerivedIntPair2() {
        val clazz = IntPair2::class
        assertTrue(clazz.isOpen || clazz.isAbstract, "Класс должен допускать наследование")
        assertTrue(clazz.constructors.size >= 2, "Должно быть как минимум два конструктора")
        assertTrue(clazz.constructors.any { it.parameters.size == 2 && isInt(it.parameters[0].type) && isInt(it.parameters[1].type)},"Должно быть конструктор (Int, Int)")
        assertTrue(clazz.constructors.any { it.parameters.isEmpty() && "PRIVATE" == it.visibility?.name }, "Должно быть конструктор без параметров")
        assertEquals(2, clazz.memberProperties.size, "Должно быть два свойства!")
        assertTrue(clazz.memberProperties.any { it is KMutableProperty<*> && it.name == "valueX" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueX : Int")
        assertTrue(clazz.memberProperties.any { it is KMutableProperty<*> && it.name == "valueY" && isInt(it.returnType) }, "Должно быть неизменяемое свойство valueY : Int")
        assertTrue(clazz.isAbstract, "Класс должен быть абстрактным, т.к. должен содержать абстрактный метод gcd")
        assertTrue(clazz.memberFunctions.any { it.name == "sum" && !it.isAbstract && it.isOpen && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода sum" )
        assertTrue(clazz.memberFunctions.any { it.name == "prod" && !it.isAbstract && !it.isOpen && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода prod" )
        assertTrue(clazz.memberFunctions.any { it.name == "gcd" && it.isAbstract && it.parameters.size == 1 && isInt(it.returnType) },"Нет требуемого метода gcd" )
    }

    // todo(MS): https://stackoverflow.com/questions/47040783/junit-5-how-to-make-test-execution-dependent-on-another-test-passing
    @Test
    fun testMethods() {
         assertMethodResult("sum", -9, 10, 1, "Неверный ответ для sum(-9,10)")
         assertMethodResult("prod", 10, 10, 100, "Неверный ответ для prod(10,10)")
         assertMethodResult("sum", 0, -10, -10, "Неверный ответ для sum(0,-10)")
         assertMethodResult("prod", 0, -100, 0, "Неверный ответ для prod(0,-100)")
         assertMethodResult("gcd", 30, 18, 6, "Неверный ответ для gcd(30,18)")
    }

    private fun assertMethodResult(methodName: String, x: Int, y: Int, expected: Int, message: String = "") {
        try {
            val kClass = DerivedIntPair2::class
            val instance = kClass.constructors
                    .first {
                        it.parameters.size == 2 &&
                                isInt(it.parameters[0].type) &&
                                isInt(it.parameters[1].type)
                    }
                    .javaConstructor!!.newInstance(x, y)
            val result: Int = kClass.memberFunctions.first { it.name == methodName }.javaMethod!!.invoke(instance) as Int
            assertEquals(expected, result, message)
        }
        catch (e: Exception) {
            fail("Не получается вызвать метод $methodName: $e")
        }
    }

    private fun isInt(type: KType) = type.javaType.typeName == "int" || type.javaType.typeName == "java.lang.Integer"

}
