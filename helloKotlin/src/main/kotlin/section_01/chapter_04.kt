package section_01

/*
Key points:
- You use the Boolean data type Boolean to represent true and false.
- The comparison operators, all of which return a Boolean, are:
    + Equal: `==`
    + Not equal: `!=`
    + Less than: `<`
    + Greater than: `>`
    + Less than or equal: `<=`
    + Greater than or equal: `>=`
- You can use Boolean logic with && and || to combine comparison conditions.
- You use if expressions to make simple decisions based on a condition, and return a value.
- You use else and else-if within an if expression to extend the decision-making beyond a single condition.
- You can use a single line if-else expression to make your code more clear and concise.
- Short circuiting ensures that only the minimal required parts of a Boolean expression are evaluated.
- Variables and constants belong to a certain scope, beyond which you cannot use them. A scope inherits visible variables
and constants from its parent.
- while loops allow you to perform a certain task a number of times until a condition is met.
- The break statement lets you break out of a loop.
 */
fun main() {
    /* --- Comparison operators --- */
    val yes: Boolean = true
    println(yes)
    val no: Boolean = false
    println(no)

    /* -- Boolean operators -- */
    val doesOneEqualTwo = (1 == 2)
    println(doesOneEqualTwo)
    val doesOneNotEqualTwo = (1 != 2)
    println(doesOneNotEqualTwo)
    val alsoTrue = !(1 == 2)
    println(alsoTrue)

    val isOneGreaterThanTwo = (1 > 2)
    println(isOneGreaterThanTwo)
    val isOneLessThanTwo = (1 < 2)
    println(isOneLessThanTwo)

    /* -- Boolean logic -- */
    val and = true && true
    println(and)
    val or = true || false
    println(or)
    val andTrue = 1 < 2 && 4 > 3
    println(andTrue)
    val andFalse = 1 < 2 && 3 > 4
    println(andFalse)

    val orTrue = 1 < 2 || 3 > 4
    println(orTrue)
    val orFalse = 1 == 2 || 3 == 4
    println(orFalse)

    val andOr = (1 < 2 && 3 > 4) || 1 < 4
    println(andOr)

    /* -- String equality -- */
    val guess = "dog"
    val dogEqualsCat = guess == "cat"
    println(dogEqualsCat)

    val order = "cat" < "dog"
    println(order)

    /* -- The if expression -- */
    if (2 > 1) {
        println("Yes, 2 is greater than 1.")
    }

    /* -- The else expression -- */
    val animal = "Fox"

    if (animal == "Cat" || animal == "Dog") {
        println("Animal is a house pet.")
    } else {
        println("Animal is not a house pet.")
    }

    val a = 5
    val b = 10
    val min = if (a < b) a else b
    println(min)
    val max = if (a > b) a else b
    println(max)

    /* -- The else-if expression -- */
    val hourOfDay = 12
    val timeOfDay = if (hourOfDay < 6) {
        "Early morning"
    } else if (hourOfDay < 12) {
        "Morning"
    } else if (hourOfDay < 17) {
        "Afternoon"
    } else if (hourOfDay < 20) {
        "Evening"
    } else if (hourOfDay < 24) {
        "Late evening"
    } else {
        "INVALID HOUR!"
    }
    println(timeOfDay)

    /* -- Encapsulating variables -- */
    var hoursWorked = 45
    var price = 0
    if (hoursWorked > 40) {
        val hourOver40 = hoursWorked - 40
        price += hourOver40 * 50
        hoursWorked -= hourOver40
    }
    price += hoursWorked * 25
    println(price)

    /* --- Loops --- */
    /* -- While loops -- */
    var sum = 1
    while (sum < 1000) {
        sum = sum + (sum + 1)
    }
    println(sum)

    /* -- Repeat-while loops -- */
    sum = 1
    do {
        sum = sum + (sum + 1)
    } while (sum < 1000)
    println(sum)

    /* -- Breaking out of a loop -- */
    sum = 1
    while (true) {
        sum = sum + (sum + 1)
        if (sum >= 1000) {
            break
        }
    }
}