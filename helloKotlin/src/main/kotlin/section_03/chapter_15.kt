package section_03

import java.awt.Color

/*
Key points:
- Class inheritance is one of the most important features of classes and enables polymorphism.
- Subclassing is a powerful tool, but it's good to know when to subclass. Subclass when you want to extend an object
and could benefit from an "is-a" relationship between subclass and superclass, but be mindful of the inherited state and
deep class hierarchies.
- The open keyword is used to allow inheritance from classes and also allow methods to be overridden in subclasses.
- Sealed classes allow you to create a strictly defined class hierarchy that is similar to an enum class but that allow
multiple instances of each subtype to be created and hold state.
- Secondary constructors allow you to define additional constructors that take additional parameters than the primary
constructor and take different actions with those parameters.
- Nested classes allow you to namespace one class within another.
- Inner classes are nested classes that also have access to the other members of the outer class.
- Visibility modifiers allow you to control where class members and top-level declarations can be seen within your code
and projects.
 */

fun main() {
    /*--- Introducing inheritance ---*/
    val john = Person2("Johnny", "Appleseed")
    val jane = Student3("Jane", "Appleseed")

    println(john.fullName())
    println(jane.fullName())

    val history = Grade("B", 9.0, 3.0)
    jane.recordGrade(history)
    // john.recordGrade(history) // john is not a student!

    /*-- Polymorphism --*/
    fun phonebookName(person: Person2): String {
        return "${person.lastName}, ${person.firstName}"
    }

    val person = Person2("Johnny", "Appleseed")
    val oboePlayer = OboePlayer("Jane", "Appleseed")

    println(phonebookName(person))
    println(phonebookName(oboePlayer))

    /*-- Runtime hierarchy checks --*/
    var hallMonitor = Student3("Jill", "Bananapeel")
    hallMonitor = oboePlayer
    // Error: hallMonitor.minimumPracticeTime
    println(hallMonitor is OboePlayer)
    println(hallMonitor !is OboePlayer)
    println(hallMonitor is Person2)

    (oboePlayer as? BandMember)?.minimumPracticeTime

    fun afterClassActivity(student: Student3): String {
        return "Goes home!"
    }

    fun afterClassActivity(student: BandMember): String {
        return "Goes to practice!"
    }

    println(afterClassActivity(oboePlayer))
    println(afterClassActivity(oboePlayer as Student3))

    /*-- Inheritance, methods and overrides --*/
    val math = Grade("B", 9.0, 3.0)
    val science = Grade("F", 9.0, 3.0)
    val physics = Grade("F", 9.0, 3.0)
    val chemistry = Grade("F", 9.0, 3.0)

    val dom = StudentAthlete("Dom", "Grady")
    dom.recordGrade(math)
    dom.recordGrade(science)
    dom.recordGrade(physics)
    println(dom.isEligible)
    dom.recordGrade(chemistry)
    println(dom.isEligible)

    /*-- Introducing super --*/
    /*-- When to call super --*/
    /*-- Preventing inheritance --*/
    /*-- Abstract classes --*/
    val human = Human("1/1/2000")

    /*--- Sealed classes ---*/
    val circle1 = Shape.Circle(4)
    val circle2 = Shape.Circle(2)
    val square1 = Shape.Square(4)
    val square2 = Shape.Square(2)

    fun size(shape: Shape): Int {
        return when (shape) {
            is Shape.Circle -> shape.radius
            is Shape.Square -> shape.sideLength
        }
    }
    println(size(circle1))
    println(size(square2))

    /*--- Secondary constructors ---*/
    /*--- Nested and inner classes ---*/
    val mazda = Car("mazda")
    val mazdaEngine = mazda.Engine("rotary")
    println(mazdaEngine)

    /*--- Visibility modifiers ---*/
    val privilegedUser = PrivilegedUser("sashika", "1234", 21)
    val privilege = Privilege(1, "invisibility")
    privilegedUser.addPrivilege(privilege)
    println(privilegedUser.about())

    /*--- When and why to subclass ---*/
    
}

open class Person2(
    var firstName: String,
    var lastName: String
) {
    fun fullName() = "$firstName $lastName"
}

open class Student3(
    firstName: String,
    lastName: String,
    var grades: MutableList<Grade> = mutableListOf()
): Person2(firstName, lastName) {
    open fun recordGrade(grade: Grade) {
        grades.add(grade)
    }
}

open class BandMember(
    firstName: String,
    lastName: String
): Student3(firstName, lastName) {
    open val minimumPracticeTime: Int
        get() { return 2 }
}

class OboePlayer(
    firstName: String,
    lastName: String
): BandMember(firstName, lastName) {
    override val minimumPracticeTime: Int =
        super.minimumPracticeTime * 2
}

class StudentAthlete(
    firstName: String,
    lastName: String
): Student3(firstName, lastName) {
    val failedClasses = mutableListOf<Grade>()

    override fun recordGrade(grade: Grade) {
        super.recordGrade(grade)

        if (grade.letter == "F") {
            failedClasses.add(grade)
        }
    }

    val isEligible: Boolean
        get() = failedClasses.size < 3
}

abstract class Mammal(val birthDate: String) {
    abstract fun consumeFood()
}

class Human(birthDate: String): Mammal(birthDate) {
    override fun consumeFood() {
        TODO("Not yet implemented")
    }

    fun createBirthCertificate() {

    }
}

sealed class Shape {
    class Circle(val radius: Int): Shape()
    class Square(val sideLength: Int): Shape()
}

open class Shape2 {
    constructor(size: Int) {

    }

    constructor(size: Int, color: String): this(size) {

    }
}
class Circle2: Shape2 {
    constructor(size: Int): super(size) {

    }

    constructor(size: Int, color: String): super(size, color) {

    }
}

class Car(val carName: String) {
    inner class Engine(val engineName: String) {
        override fun toString(): String {
            return "$engineName engine in $carName"
        }
    }
}

data class Privilege(
    val id: Int,
    val name: String
)

open class User(
    val userName: String,
    private val id: String,
    protected var age: Int
)

class PrivilegedUser(
    userName: String,
    id: String,
    age: Int
): User(userName, id, age) {
    private val privileges = mutableListOf<Privilege>()

    fun addPrivilege(privilege: Privilege) {
        privileges.add(privilege)
    }

    fun hasPrivilege(id: Int): Boolean {
        return privileges.map { it.id }.contains(id)
    }

    fun about(): String {
        // return "$userName, $id" // Error: id is private
        return "$userName, $age"
    }
}