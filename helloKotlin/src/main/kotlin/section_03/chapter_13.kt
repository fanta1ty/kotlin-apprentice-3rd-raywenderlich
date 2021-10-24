package section_03

import kotlin.math.atan
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.properties.Delegates

/*
Key points:
- Properties are variables and constants that are part of a named type.
- Default values can be used to assign a value to a property within the class definition.
- Property initializers and the init block are used to ensure that the properties of an object are initialized when the
object is created.
- Custom accessors are used to execute custom code when a property is accessed or set.
- The companion object holds properties that are universal to all instances of a particular class.
- Delegated properties are used when you want to observe, limit or lazily create a property. You'll want to use lazy
properties when a property's initial value is computationally intensive or when you won't know the initial value of a
property until after you've initialized the object.
- lateinit can be used to defer setting the value of a property reference until after the instance is created.
- Extension properties allow you to add properties to a class outside of the class definition, for example, if you're
using a class from a library.
 */

fun main() {
    /*--- Constructor properties ---*/
    val contact = Contact("Grace Murray", "grace@navy.mil")
    val name = contact.fullName
    println(name)
    val email = contact.emailAddress
    println(email)

    contact.fullName = "Grace Hopper"
    val grace = contact.fullName
    println(grace)

    var contact2 = Contact2("Grace Murray", "grace@navy.mil")
    // Error: Val cannot be reassigned
    // contact2.emailAddress = "grace@gmail.com"

    /*-- Default values --*/
    var contact3 = Contact3(
        "Grace Murray",
        "grace@navy.mil"
    )
    println(contact3.type)
    contact3.type = "Work"
    println(contact3.type)

    var workContact = Contact3(
        fullName = "Grace Murray",
        emailAddress = "grace@navy.mil",
        type = "Work"
    )

    /*--- Property initializers ---*/
    val person = Person("Grace", "Hopper")
    println(person.fullName)
    val address = Address()

    /*--- Custom accessors ---*/
    /*-- Custom getter --*/
    val tv = TV(53.93, 95.87)
    val size = tv.diagonal
    println(size)
    tv.width = tv.height
    val diagonal = tv.diagonal
    println(diagonal)

    /*-- Custom setter --*/
    tv.diagonal = 70
    println(tv.height)
    println(tv.width)

    /*--- Companion object properties ---*/
    val level1 = Level(1, "Chameleon", true)
    val level2 = Level(2, "Squid", false)
    val level3 = Level(3, "Chupacabra", false)
    val level4 = Level(4, "Yeti", false)

    // Error: Unresolved reference
    // Can't access members of the companion object on an instance
    // val highestLevel = level3.highestLevel
    val highestLevel = Level.highestLevel
    println(highestLevel)

    /*--- Delegated properties ---*/
    /*-- Observable properties --*/
    val delegatedLevel1 = DelegatedLevel(1,"Chameleon")
    val delegatedLevel2 = DelegatedLevel(2,"Squid")
    println(DelegatedLevel.highestLevel)
    delegatedLevel2.unlocked = true
    println(DelegatedLevel.highestLevel)

    /*-- Limiting a variable --*/
    val light = LightBulb()
    light.current = 50
    var current = light.current
    println(current)
    light.current = 40
    current = light.current
    println(current)

    /*-- Lazy properties --*/
    val circle = Circle(5.0)
    val circumference = circle.circumference
    println(circumference)

    /*--- Lateinit ---*/
    val lamp = Lamp()
    // ... lamp has no lightbulb, need to buy some!
    // println(lamp.bulb)
    // Error:
    // lateinit property bulb has not been initialized

    // ... bought some new ones
    lamp.bulb = LightBulb()

    /*--- Extension properties ---*/
    val unitCircle = Circle(1.0)
    println(unitCircle.diameter)
}

class Contact(
    var fullName: String,
    var emailAddress: String
)
class Contact2(
    var fullName: String,
    val emailAddress: String
)
class Contact3(
    var fullName: String,
    val emailAddress: String,
    var type: String = "Friend"
)

class Address {
    var address1: String
    var address2: String? = null
    var city = ""
    var state: String

    init {
        address1 = ""
        state = ""
    }
}

class TV(
    var height: Double,
    var width: Double
) {
    // 1
    var diagonal: Int
        get() {
            // 2
            val result = sqrt(height * height + width * width)

            // 3
            return result.roundToInt()
        }
        set(value) {
            val ratioWidth = 16.0
            val ratioHeight = 9.0
            val ratioDiagonal = sqrt(
                ratioWidth * ratioWidth + ratioHeight * ratioHeight
            )
            height = value.toDouble() * ratioHeight / ratioDiagonal
            width = height * ratioWidth / ratioHeight
        }
}
class Level(
    val id: Int,
    var boss: String,
    var unlocked: Boolean
) {
    companion object {
        var highestLevel = 1
    }
}

class DelegatedLevel(
    val id: Int,
    var boss: String
) {
    companion object {
        var highestLevel = 1
    }

    var unlocked: Boolean by Delegates.observable(false) { _, old, new ->
        if (new && id > highestLevel) {
            highestLevel = id
        }

        println("$old -> $new")
    }
}

class LightBulb {
    companion object {
        const val maxCurrent = 40
    }

    var current by Delegates.vetoable(0) { _, _, new ->
        if (new > maxCurrent) {
            println("Current too high, falling back to previous setting.")
            false
        } else {
            true
        }
    }
}
class Circle(
    var radius: Double = 0.0
) {
    val pi: Double by lazy {
        ((4.0 * atan(1.0 / 5.0)) - atan(1.0 / 239.0)) * 4.0
    }

    val circumference: Double
        get() = pi * radius * 2
}
class Lamp {
    lateinit var bulb: LightBulb
}

val Circle.diameter: Double
    get() = 2.0 * radius