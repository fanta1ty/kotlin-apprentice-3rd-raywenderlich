package section_03

import java.util.*

/*
Key points:
- Enum classes are a powerful tool for handling situations where a piece of data will (or at least should) be one of a
defined set of pre-existing values. Enum classes come with a number of tools for free, such as getting a list of all the
declared cases, and the ability to access the order and names of the cases.
- Sealed classes are a powerful tool for handling situations where a piece of data will (or at least should) be one of a
defined set of pre-existing types.
- Both enum classes and sealed classes let you take advantage of Kotlin's powerful when expression to clearly outline how
you want to handle various situations.
- Enum classes are particularly useful for creating, updating, and cleaning information about the current state in a
state machine.
 */

fun main() {
    /*--- Creating your first enum class ---*/
    for (day in DayOfTheWeek.values()) {
        println("Day ${day.ordinal}: ${day.name}, is weekend: ${day.isWeekend}")
    }

    val dayIndex = 0
    val dayAtIndex = DayOfTheWeek.values()[dayIndex]
    println("Day at $dayIndex is $dayAtIndex")

    val tuesday = DayOfTheWeek.valueOf("Tuesday")
    println("Tuesday is day ${tuesday.ordinal}")

    /*-- Updating case order --*/
    /*--- Enum class properties and functions ---*/
    val today = DayOfTheWeek.today()
    val isWeekend = "It is ${if (today.isWeekend) "" else "not"} the weekend"
    println("It is $today. $isWeekend")

    val secondDay = DayOfTheWeek.Friday
    val daysUntil = today.daysUntil(secondDay)
    println("It is $today. $isWeekend. There are $daysUntil days until $secondDay")

    /*--- Using when with enum classes ---*/
    when (today) {
        DayOfTheWeek.Monday -> println("I don't care if $today's blue")
        DayOfTheWeek.Tuesday -> println("$today's gray")
        DayOfTheWeek.Wednesday -> println("And $today, too")
        DayOfTheWeek.Thursday -> println("$today, I don't care 'bout you")
        DayOfTheWeek.Friday -> println("It's $today, I'm in love")
        DayOfTheWeek.Saturday -> println("$today, Wait...")
        DayOfTheWeek.Sunday -> println("$today always comes too late")
    }

    /*--- Sealed classes vs enum classes ---*/
    /*-- Creating a sealed class --*/
    val currency = AcceptedCurrency.Crypto()
    println("You've got some ${currency.name}!")
    currency.amount = .27541f
    println("${currency.amount} of ${currency.name} is ${currency.totalValueInDollars()} in Dollars")

    /*--- Enumeration as state machine ---*/
    Downloader().downloadData("foo.com/bar", progress = { downloadState ->
        when (downloadState) {
            null -> println("No download state yet")
            DownloadState.Starting -> println("Starting download...")
            DownloadState.InProgress -> println("Downloading data...")
            DownloadState.Error -> println("An error occurred. Download terminated")
            DownloadState.Success -> println("Download completed successfully.")
        }
    }, completion = { error, list ->
        error?.let { println("Got error: ${error.message}") }
        list?.let { println("Got list with ${list.size} items") }
    })

    /*--- Nullables and enums ---*/
}

enum class DayOfTheWeek(
    val isWeekend: Boolean = false
) {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday(true),
    Sunday(true);

    fun daysUntil(other: DayOfTheWeek): Int {
        return if (this.ordinal < other.ordinal) {
            other.ordinal - this.ordinal
        } else {
            other.ordinal - this.ordinal + DayOfTheWeek.values().count()
        }
    }
    companion object {
        fun today(): DayOfTheWeek {
            val calendarDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            var adjustedDay = calendarDayOfWeek - 2
            val days = DayOfTheWeek.values()
            if (adjustedDay < 0) {
                adjustedDay += days.count()
            }
            val today = days.first { it.ordinal == adjustedDay }
            return today
        }
    }
}

sealed class AcceptedCurrency {
    abstract val valueInDollars: Float

    class Dollar: AcceptedCurrency() {
        override val valueInDollars: Float = 1.0f
    }
    class Euro: AcceptedCurrency() {
        override val valueInDollars: Float = 1.25f
    }
    class Crypto: AcceptedCurrency() {
        override val valueInDollars: Float = 2534.92f
    }

    var amount: Float = 0.0f

    val name: String
        get() = when (this) {
            is Euro -> "Euro"
            is Dollar -> "Dollars"
            is Crypto -> "NerdCoin"
        }

    fun totalValueInDollars(): Float { return amount * valueInDollars }
}