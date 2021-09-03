package section_01

/*
Key points:
- Type conversion allows you to convert values of one type into another.
- Kotlin will convert types for you when using an operator, such as the basic arithmetic operators (+,-,*,/), with mixed
types.
- Type inference allows you to omit the type when Kotlin already knows it.
- Unicode is the standard for mapping characters to numbers.
- A simple mapping in Unicode is called a code point.
- The Character data type stores single characters. The String data type stores collections of characters, or strings.
- You can combine strings by using the addition operator.
- You can use string templates to build a string in-place.
- You can use Pairs and Triples to group data into a single data type.
- There are many kinds of numeric types with different storage and precision capabilities.
- Any is the mother of all non-nullable types, Unit is kind of like void in Java, and Nothing is well, nothing.
 */

fun main() {
    /* --- Type conversion---*/
    var integer: Int = 100
    var decimal: Double = 12.5
    integer = decimal.toInt()

    /* -- Operators with mixed types --*/
    val hourlyRate: Double = 19.5
    val hoursWorked: Int = 10
    val totalCost: Double = hourlyRate * hoursWorked.toDouble()
    println(totalCost)
    val totalCost2: Double = hourlyRate * hoursWorked
    println(totalCost2)

    /* -- Type inference --*/
    val typeInferredInt = 42
    val typeInferredDouble = 3.14

    val wantADouble = 3
    val actuallyDouble = 3.toDouble()

    /* --- Strings ---*/
    /* -- Characters and strings --*/
    val characterA: Char = 'a'
    println(characterA)

    val stringDog: String = "Dog"
    println(stringDog)

    /* -- Concatenation --*/
    var message = "Hello" + " my name is "
    println(message)
    val name = "Joe"
    message += name
    println(message)

    val exclamationMark: Char = '!'
    message += exclamationMark
    println(message)

    /* -- String templates --*/
    message = "Hello my name is $name!"
    println(message)

    val oneThird = 1.0 / 3.0
    val oneThirdLongString = "One third is $oneThird as a decimal"
    println(oneThirdLongString)
    val oneThirdLongString2 = "One third is ${1.0 / 3.0} as a decimal"
    println(oneThirdLongString2)

    /* -- Multi-line strings --*/
    val bigString = """
        |You can have a string
        |that contains multiple
        |lines
        |by
        |doing this.
        """.trimMargin()
    println(bigString)

    /* --- Pairs and Triples ---*/
    val coordinates: Pair<Int, Int> = Pair(2, 3)
    println(coordinates)
    val coordinatesInferred = Pair(2, 3)
    println(coordinatesInferred)
    val coordinatesWithTo = 2 to 3
    println(coordinatesWithTo)
    val coordinatesDoubles = Pair(2.1, 3.5)
    println(coordinatesDoubles)
    val coordinatesMixed = Pair(2.1, 3)
    println(coordinatesMixed)

    val x1 = coordinates.first
    println(x1)
    val y1 = coordinates.second
    println(y1)

    val (x, y) = coordinates
    println("x: $x, y: $y")

    val coordinates3D = Triple(2, 3, 1)
    val (x3, y3, z3) = coordinates3D
    println("x3: $x3, y3: $y3, z3: $z3")
    val (x4, y4, _) = coordinates3D
    println("x4: $x4, y4: $y4")

    /* --- Number types ---*/
    val a: Short = 12
    val b: Byte = 120
    val c: Int = -100_000
    val answer = a + b + c // Answer will be an Int
    println(answer)

    /* --- Any, Unit, and Nothing Types ---*/
    val anyNumber: Any = 42
    println(anyNumber)
    val anyString: Any = "42"
    println(anyString)

    fun add(): Unit {
        val result = 2 + 2
        println(result)
    }
    add()
}