package section_02

/*
Key points:
- Arrays are ordered collections of values of the same type.
- There are special classes such as IntArray created as array of Java primitive types.
- Lists are similar to arrays but have the additional feature of being dynamically-sized.
- You can add, remove, update, and insert elements into mutable lists.
- Use indexing or one of many methods to access and update elements.
- Be wary of accessing an index that's out of bounds.
- You can iterate over the elements of an array or list using a for loop or using forEach.
- You can check for elements in an array or list using in.
- Special consideration should be given when working with nullable lists and lists with nullable elements.
 */

fun main(args: Array<String>) {
    /* --- Arrays --- */
    /* -- What is an array? -- */
    /* -- When are arrays useful? -- */
    /* -- Creating arrays? -- */
    val evenNumbers = arrayOf(2, 4, 6, 8)
    println(evenNumbers.joinToString(" "))

    val fiveFives = Array(5) { 5 }
    println(fiveFives.joinToString(" "))

    val vowels = arrayOf("a", "e", "i", "o", "u")
    println(vowels.joinToString(" "))

    /* -- Arrays of primitive types -- */
    val oddNumbers = intArrayOf(1, 3, 5, 7)
    println(oddNumbers.joinToString(" "))

    val zeros = DoubleArray(4)
    println(zeros.joinToString(" "))

    val otherOddNumbers = arrayOf(1, 3, 5, 7).toIntArray()
    println(otherOddNumbers.joinToString(" "))

    /* -- Arguments to main() -- */
    /* -- Iterating over an array -- */
    for (arg in args) {
        println(arg)
    }

    args.forEach {
        println(it)
    }

    /* --- Lists --- */
    /* -- Creating lists -- */
    val innerPlanets = listOf("Mercury", "Venus", "Earth", "Mars")
    println(innerPlanets.joinToString(" "))
    val innerPlanetsArrayList = arrayListOf("Mercury", "Venus", "Earth", "Mars")
    println(innerPlanetsArrayList.joinToString(" "))
    val subscribers: List<String> = listOf()
    val subscribers2 = listOf<String>()

    /* -- Mutable lists -- */
    val outerPlanets = mutableListOf("Jupiter", "Saturn", "Uranus", "Neptune")
    println(outerPlanets.joinToString(" "))
    val exoPlanets = mutableListOf<String>()

    /* --- Accessing elements --- */
    /* -- Using properties and methods -- */
    val players = mutableListOf("Alice", "Bob", "Cindy", "Dan")
    println(players.joinToString(" "))
    println(players.isEmpty())

    if (players.size < 2) {
        println("We need at least two players!")
    } else {
        println("Let's start!")
    }

    var currentPlayer = players.first()
    println(currentPlayer)
    println(players.last())

    val minPlayer = players.minOrNull()
    minPlayer.let {
        println("$minPlayer will start")
    }
    val maxPlayer = players.maxOrNull()
    if (maxPlayer != null) {
        println("$maxPlayer is the MAX")
    }

    /* -- Using indexing -- */
    val firstPlayer = players[0]
    println("First player is $firstPlayer")
    val secondPlayer = players.get(1)
    println("Second player is $secondPlayer")

    /* -- Using ranges to slice -- */
    val upcomingPlayersSlice = players.slice(1..2)
    println(upcomingPlayersSlice.joinToString())

    /* -- Checking for an element -- */
    fun isEliminated(player: String): Boolean {
        return player !in players
    }
    println(isEliminated("Bob"))
    println(players.slice(1..3).contains("Alice"))

    /* --- Modifying lists --- */
    /* -- Appending elements -- */
    players.add("Ali")
    println(players.joinToString())
    players += "Gina"
    println(players.joinToString())

    /* -- Inserting elements -- */
    players.add(5, "Frank")
    println(players.joinToString())

    /* -- Removing elements -- */
    val wasPlayerRemoved = players.remove("Gina")
    println("It is $wasPlayerRemoved that Gina was removed")
    val removedPlayer = players.removeAt(2)
    println("$removedPlayer was removed")

    /* --- Updating elements --- */
    players[4] = "Franklin"
    println(players.joinToString())

    players[3] = "Anna"
    players.sort()
    println(players.joinToString())

    val arrayOfInts = arrayOf(1, 2, 3)
    arrayOfInts[0] = 4
    println(arrayOfInts.joinToString())

    /* --- Iterating through a list --- */
    val scores = listOf(2, 2, 8, 6, 1)
    for (player in players) {
        println(player)
    }

    for ((index, player) in players.withIndex()) {
        println("${index + 1}. $player")
    }

    fun sumOfElements(list: List<Int>): Int {
        var sum = 0
        for (number in list) {
            sum += number
        }
        return sum
    }

    println(sumOfElements(scores))

    /* --- Nullability and collection types --- */
    var nullableList: List<Int>? = listOf(1, 2, 3, 4)
    nullableList = null
    var listOfNullables: List<Int?> = listOf(1, 2, null, 4)
    var nullableListOfNullables: List<Int?>? = listOf(1, 2, null, 4)
    nullableListOfNullables = null
}