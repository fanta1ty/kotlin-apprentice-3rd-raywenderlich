package section_01


/*
Key points:
- You can use ranges to create a sequence of numbers, incrementing to move from one value to another.
- Closed ranges include both the start and end values
- Half-open ranges include the start value and stop before the end value.
- For loops allow you to iterate over a range.
- The continue statement lets you finish the current iteration of a loop and begin the next iteration.
- Labeled statements let you use break and continue on an outer loop.
- You use when expressions to decide which code to run depending on the value of a variable or constant.
- The power of a when expression comes from leveraging pattern matching to compare values using complex rules.
 */

fun main() {
    /* --- Ranges --- */
    val closedRange = 0..5
    println(closedRange)
    val halfOpenRange = 0 until 5
    println(halfOpenRange)
    val decreasingRange = 5 downTo 0
    println(decreasingRange)

    /* --- For loops --- */
    val count = 10
    var sum = 0
    for (i in 1..count) {
        println(i)
        sum += i
        println(sum)
    }

    /* -- Repeat loops -- */
    sum = 1
    var lastSum = 0
    repeat(10) {
        val temp = sum
        sum += lastSum
        lastSum = temp

        println(lastSum)
    }

    /* -- Steps in loops -- */
    sum = 0
    for (i in 1..count step 2) {
        println(i)
        sum += i
        println(sum)
    }

    sum = 0
    for (i in count downTo 1 step 2) {
        println(i)
        sum += i
        println(sum)
    }

    /* -- Labeled statements -- */
    sum = 0
    for (row in 0 until 8) {
        if (row % 2 == 0) {
            continue
        }

        for (column in 0 until 8) {
            sum += row * column
            println(column)
            println(sum)
        }
    }

    sum = 0
    rowLoop@ for (row in 0 until 8) {
        columnLoop@ for (column in 0 until 8) {
            if (row == column) {
                continue@rowLoop
            }
            sum += row * column
        }
    }

    /* --- When expressions --- */
    val number = 10
    when (number) {
        0 -> println("Zero")
        else -> println("Non-zero")
    }

    when (number) {
        10 -> println("It's ten!")
    }

    val string = "Dog"
    when (string) {
        "Cat", "Dog" -> println("Animal is a house pet.")
        else -> println("Animal is not a house pet.")
    }

    /* -- Returning values -- */
    val numberName = when (number) {
        2 -> "two"
        4 -> "four"
        6 -> "six"
        8 -> "eight"
        10 -> "ten"
        else -> {
            println("Unknown number")
            "Unknown"
        }
    }
    println(numberName)

    /* -- Advanced when expressions -- */
    val hourOfDay = 12
    var timeOfDay: String

    timeOfDay = when (hourOfDay) {
        0, 1, 2, 3, 4, 5 -> "Early morning"
        6, 7, 8, 9, 10, 11 -> "Morning"
        12, 13, 14, 15, 16 -> "Afternoon"
        17, 18, 19 -> "Evening"
        20, 21, 22, 23 -> "Late evening"
        else -> "INVALID HOUR!"
    }
    println(timeOfDay)

    /* -- Using when expressions with ranges -- */
    timeOfDay = when (hourOfDay) {
        in 0..5 -> "Early morning"
        in 6..11 -> "Morning"
        in 12..16 -> "Afternoon"
        in 17..19 -> "Evening"
        in 20..23 -> "Late evening"
        else -> "INVALID HOUR!"
    }
    println(timeOfDay)

    /* -- Using when expressions with conditions -- */
    when {
        number % 2 == 0 -> println("Even")
        else -> println("Odd")
    }

    val (x, y, z) = Triple(3, 2, 5)
    when {
        x == 0 && y == 0 && z == 0 -> println("Origin")
        y == 0 && z == 0 -> println("On the x-axis at x = $x")
        x == 0 && z == 0 -> println("On the y-axis at y = $y")
        x == 0 && y == 0 -> println("On the z-axis at z = $z")
        else -> println("In space at x = $x, y = $y, z = $z")
    }
}