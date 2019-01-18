package ru.kotlin.sirius2019.part2.classes

/**
 * 1. Объявите класс IntPair1 такой, чтобы он
 *    - содержал неявные методы hashCode(), equals(), toString(), component1(), component2() и copy()
 *    - содержал неизменяемые свойства valueX и valueY типа Int
 *    - конструктор без параметров (valueX и valueY инициализируются значениями -1)
 *    - конструктор с двумя параметрами - valueX и valueY
 */

class IntPair1

/**
 * 2. Объявите класс IntPair2 такой, чтобы он
 *    - допускал наследование
 *    - содержал изменяемые свойства valueX и valueY типа Int,
 *    - имел конструктор с двумя параметрами - valueX и valueY
 *    - имел private конструктор без параметров
 *    - имел метод переопределяемый sum, возвращающий сумму valueX и valueY - Int
 *    - имел метод непереопределяемый prod, возвращающий произведение valueX и valueY - Int
 *    - имел абстрактный метод gcd, возвращающий Int
 *
 *    Далее объявите класс-наследник DerivedIntPair2 такой, чтобы он
 *    - не допускал дальнейшего наследования
 *    - имел конструктор с двумя параметрами - valueX и valueY
 *    - переопределял метод gcd и реулизовывал в нём вычисление наибольшего общего делителя свойств valueX и valueY
 *     (см. https://younglinux.info/algorithm/euclidean)
 */

class IntPair2

class DerivedIntPair2
