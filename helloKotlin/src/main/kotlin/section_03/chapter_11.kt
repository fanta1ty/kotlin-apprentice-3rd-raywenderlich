package section_03

import kotlin.random.Random

/*
Key points:
- Classes are named type that can have properties and methods.
- Classes use references that are shared on assignment.
- Class instances are called objects.
- Objects are mutable.
- Mutability introduces state, which adds complexity when managing your objects.
- Data classes allow you to create simple model objects that avoid a lot of boilerplate for comparing, printing,
and copying objects.
- Destructuring declarations allow you to easily extract multiple properties of data class objects.
 */

fun main() {
    /* --- Creating classes --- */
    val john = Person(firstName = "Johnny", lastName = "Appleseed")
    println(john.fullName)

    /* --- Reference types --- */
    var var1 = SimplePerson(name = "John")
    var var2 = var1
    /* -- The heap vs. the stack -- */
    /* -- Working with references -- */
    var homeOwner = john
    john.firstName = "John"

    println(john.firstName)
    println(homeOwner.firstName)

    /* -- Object identity -- */
    println(homeOwner === john)

    val impostorJohn = Person(firstName = "John", lastName = "Appleseed")

    println(john === homeOwner)
    println(john === impostorJohn)
    println(impostorJohn === homeOwner)

    homeOwner = impostorJohn
    println(john === homeOwner)

    homeOwner = john
    println(john === homeOwner)

    var imposters = (0..100).map {
        Person(firstName = "John", lastName = "Appleseed")
    }

    imposters.map {
        it.firstName == "John" && it.lastName == "Appleseed"
    }.contains(true)
    println(imposters.contains(john))

    val mutableImposters = mutableListOf<Person>()
    mutableImposters.addAll(imposters)
    mutableImposters.contains(john)

    mutableImposters.add(Random.nextInt(5), john)
    mutableImposters.contains(john)

    val indexOfJohn = mutableImposters.indexOf(john)

    if (indexOfJohn != -1) {
        mutableImposters[indexOfJohn].lastName = "Bananapeel"
    }
    println(john.fullName)

    /* -- Methods and mutability -- */
    val jane = Student(firstName = "Jane", lastName = "Appleseed")
    val history = Grade(letter = "B", points = 9.0, credits = 3.0)
    var math = Grade(letter = "A", points = 16.0, credits = 4.0)

    jane.recordGrade(history)
    jane.recordGrade(math)

    /* -- Mutability and constants -- */
    /* --- Understanding state and side effects --- */
    /* --- Data classes --- */
    val marie = StudentData("Marie", "Curie", 1)
    val emmy = StudentData("Emmy", "Noether", 2)
    val marieCopy = marie.copy()

    println(marie)
    println(emmy)
    println(marie == emmy)
    println(marie == marieCopy)
    println(marie === marieCopy)

    /* -- Destructing declarations -- */
    val (firstName, lastName, id) = marie
    println(firstName)
    println(lastName)
    println(id)
}

class Person(var firstName: String, var lastName: String) {
    val fullName
        get() = "$firstName $lastName"
}

class SimplePerson(val name: String)

class Grade(
    val letter: String,
    val points: Double,
    val credits: Double
)

class Student(
    val firstName: String,
    val lastName: String,
    val grades: MutableList<Grade> = mutableListOf(),
    var credits: Double = 0.0
) {
    fun recordGrade(grade: Grade) {
        grades.add(grade)
        credits += grade.credits
    }
}

data class StudentData(
    var firstName: String,
    var lastName: String,
    var id: Int
)