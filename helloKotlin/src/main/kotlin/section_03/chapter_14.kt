package section_03

/*
Key points:
- Methods are behaviors that extend the functionality of a class.
- A typical method is a function defined inside of a class or object.
- A method can access the value of an instance by using the keyword this.
- Companion object methods add behavior to a class instead of the instances of that class. To define a companion object
method, you add a function in the class companion object block.
- You can augment an existing class definition and add methods to it using extension methods.
 */

fun main() {
    /*--- Method refresher ---*/
    val numbers = arrayListOf(1, 2, 3)
    numbers.removeAt(numbers.lastIndex)
    println(numbers)

    /*-- Comparing methods to getters and setters --*/
    /*-- Turning a function into a method --*/
    val months = arrayOf(
        "January", "February", "March",
        "April", "May", "June",
        "July", "August", "September",
        "October", "November", "December"
    )

    class SimpleDate1(var month: String)

    fun monthsUntilWinterBreak(from: SimpleDate1): Int {
        return months.indexOf("December") - months.indexOf(from.month)
    }

    class SimpleDate2(var month: String) {
        fun monthsUntilWinterBreak(from: SimpleDate2): Int {
            return months.indexOf("December") - months.indexOf(from.month)
        }
    }
    val date2 = SimpleDate2("October")
    println(date2.monthsUntilWinterBreak(date2))

    /*--- Introducing this ---*/
    class SimpleDate3(var month: String) {
        fun monthsUntilWinterBreak(): Int {
            return months.indexOf("December") - months.indexOf(this.month)
        }
    }

    val date3 = SimpleDate3("September")
    date3.monthsUntilWinterBreak()

    /*--- Object methods ---*/
    val factorial = MyMath.factorial(6)
    println(factorial)

    /*--- Extension methods ---*/
    fun SimpleDate3.monthsUntilSummerBreak(): Int {
        val monthIndex = months.indexOf(month)
        return if (monthIndex in 0..months.indexOf("June")) {
            months.indexOf("June") - months.indexOf(month)
        } else if (monthIndex in months.indexOf("June")..months.indexOf("August")) {
            0
        } else {
            months.indexOf("June") + (12 - months.indexOf(month))
        }
    }
    date3.month = "December"
    println(date3.monthsUntilSummerBreak())

    fun Int.abs(): Int {
        return if (this < 0) -this else this
    }

    println(4.abs())
    println((-4).abs())

    /*--- Companion object extensions ---*/
    fun MyMath.Companion.primeFactors(value: Int): List<Int> {
        var remainingValue = value
        var testFactor = 2
        val primes = mutableListOf<Int>()
        while (testFactor * testFactor <= remainingValue) {
            if (remainingValue % testFactor == 0) {
                primes.add(testFactor)
                remainingValue /= testFactor
            } else {
                testFactor += 1
            }
        }

        if (remainingValue > 1) {
            primes.add(remainingValue)
        }

        return primes
    }

    println(MyMath.Companion.primeFactors(41))
}

class MyMath {
    companion object {
        fun factorial(number: Int): Int {
            return (1..number).fold(1) { a, b -> a * b }
        }
    }
}