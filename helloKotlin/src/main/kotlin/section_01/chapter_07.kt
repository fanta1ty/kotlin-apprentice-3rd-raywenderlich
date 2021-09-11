package section_01

/*
Key points:
- null represents the absence of a value
- Non-null variables and constants must always have a non-null value
- Nullable variables and constants are like boxes that can contain a value or be empty (null)
- To work with the value inside a nullable, you must typically first check that the value is not null
- The safest ways to work with a nullable's value is by using safe calls or the Elvis operator. Use not-null assertions
only when appropriate, as they could produce a runtime error.
 */

fun main() {
    /* --- Introducing null --- */
    /* -- Sentinel values -- */

    /* --- Introducing nullable types --- */
    var errorCode: Int?
    errorCode = 100
    println(errorCode)

    errorCode = null
    println(errorCode)

    /* --- Checking for null --- */
    /* -- Not-null assertion operator -- */
    var authorName: String? = "Joe Howard"
    var authorAge: Int? = 24

    val ageAfterBirthday = authorAge!! + 1
    println("After their next birthday, author will be $ageAfterBirthday")

    /* -- Smart casts -- */
    var nonNullableAuthor: String
    var nullableAuthor: String?

    if (authorName != null) {
        nonNullableAuthor = authorName
        println(nonNullableAuthor)
    } else {
        nullableAuthor = authorName
        println(nullableAuthor)
    }

    /* --- Safe calls --- */
    var nameLength = authorName?.length
    println("Author's name has length $nameLength")

    val nameLengthPlus5 = authorName?.length?.plus(5)
    println("Author's name length plus 5 is $nameLengthPlus5")

    /* -- The let() function -- */
    authorName?.let {
        nonNullableAuthor = authorName
    }

    authorName?.let {
        nameLength = authorName.length
    }

    /* --- Elvis operator --- */
    var nullableInt: Int? = 10
    var mustHaveResult = nullableInt ?: 0

    println(mustHaveResult)

    nullableInt = null
    mustHaveResult = nullableInt ?: 0
    println(mustHaveResult)
}