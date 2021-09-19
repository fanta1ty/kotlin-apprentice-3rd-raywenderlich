package section_02

/*
Key points:
- A map is an unordered collection of key-value pairs.
- The keys of a map are all of the same type, and the values are all of the same type.
- Use indexing to get values and to add, update or remove pairs.
- If a key is not in a map, lookup return null.
- Built-in Kotlin types such as String, Int, Double have efficient hash values out of the box.
- Use HashMap<K, V> for performance critical code.
- Sets are unordered collections of unique value of the same type.
- Sets are most useful when you need to know whether something is included in the collection or not.
 */

fun main() {
    /* --- Creating maps --- */
    var yearOfBirth = mapOf(
        "Anna" to 1990,
        "Brian" to 1991,
        "Craig" to 1992,
        "Donna" to 1993
    )
    println(yearOfBirth)
    var namesAndScores = mutableMapOf(
        "Anna" to 2,
        "Brian" to 2,
        "Craig" to 8,
        "Donna" to 6
    )
    println(namesAndScores)
    namesAndScores = mutableMapOf()
    var pairs = HashMap<String, Int>()
    pairs = HashMap<String, Int>(20)

    /* --- Accessing values --- */
    /* -- Using the index operator -- */
    namesAndScores = mutableMapOf(
        "Anna" to 2,
        "Brian" to 2,
        "Craig" to 8,
        "Donna" to 6
    )
    println(namesAndScores["Anna"])
    println(namesAndScores["Greg"])

    /* -- Using properties and methods -- */
    println(namesAndScores.get("Craig"))
    println(namesAndScores.isEmpty())
    println(namesAndScores.size)

    /* --- Modifying mutable maps --- */
    /* -- Adding pairs -- */
    val bobData = mutableMapOf(
        "name" to "Bob",
        "profession" to "CardPlayer",
        "country" to "USA"
    )
    println(bobData)
    bobData.put("state", "CA")
    println(bobData)
    bobData["city"] = "San Francisco"
    println(bobData)

    /* -- Updating values -- */
    bobData.put("name", "Bobby")
    bobData["profession"] = "Mailman"
    println(bobData)

    val pair = "nickname" to "Bobby D"
    bobData += pair
    println(bobData)

    /* -- Removing pairs -- */
    bobData.remove("city")
    bobData.remove("state", "CA")
    println(bobData)

    /* --- Iterating through maps --- */
    for ((player, score) in namesAndScores) {
        println("$player - $score")
    }

    for (player in namesAndScores.keys) {
        println("$player, ")
    }

    /* --- Running time for map operations --- */
    println("some string".hashCode())
    println(1.hashCode())
    println(false.hashCode())

    /* --- Sets --- */
    /* -- Creating sets -- */
    val names = setOf("Anna", "Brian", "Craig", "Anna")
    println(names)
    val hashSet = HashSet<Int>()

    /* -- Set from arrays -- */
    val someArray = arrayOf(1, 2, 3, 1)
    var someSet = mutableSetOf(*someArray)
    println(someSet)

    /* -- Accessing elements -- */
    println(someSet.contains(1))
    println(4 in someSet)

    for (number in someSet) {
        println(number)
    }

    /* -- Adding and removing elements -- */
    someSet.add(5)
    val removedOne = someSet.remove(1)
    println(removedOne)

    println(someSet)
}