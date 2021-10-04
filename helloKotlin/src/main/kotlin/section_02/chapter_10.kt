package section_02

import java.lang.NullPointerException

/*
Key points:
- Lambdas are functions without names. They can be assigned to variables and passed as arguments to functions.
- Lambdas have shorthand syntax that makes them a lot easier to use than other functions.
- A lambda can capture the variables and constants from its surrounding context.
- A lambda can be used to direct how a collection is sorted.
- There exists a handy set of functions on collections which can be used to iterate over the collection and transform
the collection. Transforms include mapping each element to a new value, filtering out certain values, and folding or
reducing the collection down to a single value.
 */

fun main() {
    /* --- Lambda basics --- */
    var multiplyLambda: (Int, Int) -> Int

    multiplyLambda = { a: Int, b: Int -> Int
        a * b
    }

    val lambdaResult = multiplyLambda(4, 2)
    println(lambdaResult)

    /* -- Shorthand syntax -- */
    multiplyLambda = { a, b -> a * b}
    /* - it keyword - */
    var doubleLambda = { a: Int -> 2 * a }
    doubleLambda = { 2 * it }
    val square: (Int) -> Int = { it * it }

    /* -- Lambdas as arguments -- */
    fun operateOnNumbers(
        a: Int,
        b: Int,
        operation: (Int, Int) -> Int
    ): Int {
        val result = operation(a, b)
        println(result)
        return  result
    }

    val addLambda = { a: Int, b: Int -> a + b}
    operateOnNumbers(4, 2, operation = addLambda)
    fun addFunction(a: Int, b: Int) = a + b
    operateOnNumbers(4, 2, ::addFunction)
    operateOnNumbers(4, 2) { a, b ->
        a + b
    }
    operateOnNumbers(4, 2, operation = Int::plus)

    /* -- Lambdas with no meaningful return value -- */
    var unitLambda: () -> Unit = {
        println("Kotlin Apprentice is awesome!")
    }
    unitLambda()

    var nothingLambda: () -> Nothing = {
        throw NullPointerException()
    }

    /* -- Capturing from the enclosing scope -- */
    var counter = 0
    val incrementCounter = {
        counter += 1
        println(counter)
    }

    incrementCounter()
    incrementCounter()
    incrementCounter()
    incrementCounter()
    incrementCounter()

    fun countingLambda(): () -> Int {
        var counter = 0
        val incrementCounter: () -> Int = {
            counter += 1
            counter
        }

        return incrementCounter
    }

    val counter1 = countingLambda()
    val counter2 = countingLambda()

    println(counter1())
    println(counter2())

    println(counter1())
    println(counter1())
    println(counter2())

    /* --- Custom sorting with lambdas --- */
    val names = arrayOf("ZZZZZZ", "BB", "A", "CCCC", "EEEEE")
    println(names.sorted())
    val nameByLength = names.sortedWith(compareBy{
        -it.length
    })
    println(nameByLength)

    /* --- Iterating over collections with lambdas --- */
    val values = listOf(1, 2, 3, 4, 5, 6)
    values.forEach {
        println("$it: ${it * it}")
    }

    var prices = listOf(1.5, 10.0, 4.99, 2.30, 8.19)
    val largePrices = prices.filter { it > 5.0 }
    println(largePrices)

    val salePrices = prices.map { it * 0.9 }
    println(salePrices)

    val userInput = listOf("0", "11", "haha", "42")
    val numbers = userInput.map { it.toIntOrNull() }
    println(numbers)
    val numbers2 = userInput.mapNotNull { it.toIntOrNull() }
    println(numbers2)

    var sum = prices.fold(0.0) { a, b -> a + b }
    println(sum)

    sum = prices.reduce { a, b -> a + b}
    println(sum)

    val stock = mapOf(
        1.5 to 5,
        10.0 to 2,
        4.99 to 20,
        2.30 to 5,
        8.19 to 30
    )
    var stockSum = 0.0
    stock.forEach { stockSum += it.key * it.value }
    println(stockSum)
}