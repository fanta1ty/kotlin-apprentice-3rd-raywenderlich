package section_03

/*
Key points:
- The singleton pattern is used when you want only one instance of a type to be created in your app.
- The object keyword is unique to Kotlin compared with similar languages, and it gives you a built-in way to make
singletons with named objects. It also lets you make anonymous objects, the Kotlin version of Java anonymous classes.
- A class companion object gives you the Kotlin equivalent of Java static members.
- Anonymous objects - or object expressions - let you create unnamed instances of interfaces and to override class
behavior without subclassing.
- Using Show Kotlin Bytecode and decompiling in IntelliJ IDEA is an informative way to understand what the Kotlin
compiler is doing.

 */

fun main() {
    /*--- Singletons ---*/
    /*-- Named objects --*/
    val marie = Student2(1, "Marie", "Curie")
    val albert =  Student2(2, "Albert", "Einstein")
    val emmy = Student2(3, "Emmy", "Noether")

    StudentRegistry.addStudent(marie)
    StudentRegistry.addStudent(albert)
    StudentRegistry.addStudent(emmy)

    StudentRegistry.listAllStudents()

    /*-- Comparison to classes --*/
    /*--- Using static members ---*/
    /*-- Creating companion objects --*/
    val emmy2 = Scientist.newScientist("Emmy", "Noether")
    val isaac = Scientist.newScientist("Isaac", "Newton")
    val nick = Scientist.newScientist("Nikola", "Tesla")

    ScientistRepository.addScientist(emmy2)
    ScientistRepository.addScientist(isaac)
    ScientistRepository.addScientist(nick)

    ScientistRepository.listAllScientists()

    /*-- Companion naming and accessing from Java --*/
    /*--- Using anonymous objects ---*/
    val counter = object : Counts {
        override fun studentCount(): Int {
            return StudentRegistry.allStudents.size
        }

        override fun scientistCount(): Int {
            return ScientistRepository.allScientist.size
        }
    }

    println(counter.studentCount())
    println(counter.scientistCount())
}

data class Student2(
    val id: Int,
    val firstName: String,
    val lastName: String
) {
    var fullName = "$lastName, $firstName"
}

object StudentRegistry {
    val allStudents = mutableListOf<Student2>()

    fun addStudent(student: Student2) {
        allStudents.add(student)
    }

    fun removeStudent(student: Student2) {
        allStudents.remove(student)
    }

    fun listAllStudents() {
        allStudents.forEach { println(it.fullName) }
    }
}

object JsonKeys {
    const val JSON_KEY_ID = "id"
    const val JSON_KEY_FIRSTNAME = "first_name"
    const val JSON_KEY_LASTNAME = "last_name"
}

class Scientist private constructor(
    val id: Int,
    val firstName: String,
    val lastName: String
) {
    companion object {
        var currentId = 0

        fun newScientist(
            firstName: String,
            lastName: String
        ): Scientist {
            currentId += 1
            return Scientist(currentId, firstName, lastName)
        }
    }

    var fullName = "$firstName $lastName"
}
object ScientistRepository {
    val allScientist = mutableListOf<Scientist>()

    fun addScientist(scientist: Scientist) {
        allScientist.add(scientist)
    }

    fun removeScientist(scientist: Scientist) {
        allScientist.remove(scientist)
    }

    fun listAllScientists() {
        allScientist.forEach {
            println("${it.id}: ${it.fullName}")
        }
    }
}

interface Counts {
    fun studentCount(): Int
    fun scientistCount(): Int
}