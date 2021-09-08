package section_01

/*
Key points:
- You use a function to define a task, which you can execute as many times as you like without having to write the code
multiple times.
- Functions can take zero or more parameters and optionally return a value.
- For clarity at the call site you can use named arguments when calling a function.
- Specifying default function values can make those functions easier to work with and reduce the amount of code you have
to write.
- Functions can have the same name with different parameters. This is called overloading.
- You can assign functions to variables and pass them to other functions.
- Functions can have a special Nothing return type to inform Kotlin that this function will never exit.
- Strive to create functions that are clearly named and have one job with repeatable inputs and outputs.
 */

fun main() {
    /* --- Function basics --- */
    fun printMyName() {
        println("My name is Joe Howard.")
    }
    printMyName()

    /* -- Function parameters -- */
    fun printMultipleOfFive(value: Int) {
        println("$value * 5 = ${value * 5}")
    }
    printMultipleOfFive(10)

    fun printMultipleOf(multiplier: Int, andValue: Int) {
        println("$multiplier * $andValue = ${multiplier * andValue}")
    }
    printMultipleOf(4, 2)

    /* -- Parameter named arguments -- */
    /* -- Parameter default values -- */
    fun printMultipleOf2(multiplier: Int, value: Int = 1) {
        println("$multiplier * $value = ${multiplier * value}")
    }
    printMultipleOf2(4)

    /* -- Return values -- */
    fun multiply(number: Int, multiplier: Int): Int {
        return number * multiplier
    }
    println(multiply(2, 3))

    fun multiplyAndDivide(number: Int, factor: Int): Pair<Int, Int> {
        return Pair(number * factor, number / factor)
    }
    val (product, quotient) = multiplyAndDivide(4, 2)
    println("($product, $quotient)")

    fun multiplyInferred(number: Int, multiplier: Int) = number * multiplier
    println(multiplyInferred(4, 2))

    /* -- Parameters as values -- */
    fun incrementAndPrint(value: Int): Int {
        val newValue = value + 1
        println(newValue)
        return newValue
    }
    println(incrementAndPrint(1))

    /* -- Overloading -- */
    fun getValue(value: Int): Int {
        return value + 1
    }

    fun getValue(value: String): String {
        return "The value is $value"
    }

    println(getValue(2))
    println(getValue("AAA"))

    /* --- Functions as variables --- */
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    var function = ::add
    println(function(4, 2))

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    function = ::subtract
    println(function(4, 2))

    fun printResult(function: (Int, Int) -> Int, a: Int, b: Int) {
        val result = function(a, b)
        println(result)
    }
    printResult(::add, 4, 2)
}