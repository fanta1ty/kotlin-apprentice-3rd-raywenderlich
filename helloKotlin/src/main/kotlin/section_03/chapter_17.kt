package section_03

/*
Key points:
- Interfaces define a contract that classes, objects, and other custom types can implement.
- By implementing an interface, a type is required to conform to the interface by implementing all methods and
properties of the interface.
- A type can implement any number of interfaces, which allows for a quasi-multiple inheritance not permitted through
subclassing.
- The Kotlin standard library uses interfaces extensively. You can use many of them, such as Comparable, on your own
types.
 */

fun main() {
    /*--- Introducing interfaces ---*/
    /*-- Interface syntax --*/
    /*-- Methods in interfaces --*/
    val car = OptionalDirection()
    car.turn()
    car.turn(Direction.RIGHT)

    /*-- Default method implementations --*/
    val falcon = LightFreighter()
    falcon.accelerate()
    falcon.stop()

    val enterprise = Starship()
    enterprise.accelerate()
    enterprise.stop()

    /*-- Properties interfaces --*/
    /*--- Interface inheritance ---*/
    /*--- Implementing multiple interfaces ---*/
    /*--- Interfaces in the standard library ---*/
    /*-- Iterator --*/
    var cars = listOf("Lamborghini", "Ferrari", "Rolls-Royce")
    val numbers = mapOf("Brady" to 12, "Manning" to 18, "Brees" to 9)

    for (car in cars) {
        println(car)
    }

    for (qb in numbers) {
        println("${qb.key} wears ${qb.value}")
    }

    /*-- Comparable --*/
    val titanic = Boat()
    titanic.lenght = 883

    val qe2 = Boat()
    qe2.lenght = 963
    println(titanic > qe2)
}

interface Vehicle {
    fun accelerate()
    fun stop()
}

class Unicycle: Vehicle {
    var peddling = false

    override fun accelerate() {
        peddling = true
    }

    override fun stop() {
        peddling = false
    }
}

enum class Direction {
    LEFT, RIGHT
}

interface DirectionalVehicle {
    fun accelerate()
    fun stop()
    fun turn(direction: Direction)
    fun description(): String
}

interface OptionalDirectionalVehicle {
    fun turn(direction: Direction = Direction.LEFT)
}

class OptionalDirection: OptionalDirectionalVehicle {
    override fun turn(direction: Direction) {
        println(direction)
    }
}

interface SpaceVehicle {
    fun accelerate()
    fun stop() {
        println("Whoa, slow down!")
    }
}

class LightFreighter: SpaceVehicle {
    override fun accelerate() {
        println("Proceed to hyperspace!")
    }
}

class Starship: SpaceVehicle {
    override fun accelerate() {
        println("Wrap factor 9 please!")
    }

    override fun stop() {
        super.stop()
        println("That kind of hurt!")
    }
}

interface VehicleProperties {
    val weight: Int
    val name: String
        get() = "Vehicle"
}

class Car2: VehicleProperties {
    override val weight: Int = 1000
}

class Tank: VehicleProperties {
    override val weight: Int
        get() = 10000

    override val name: String
        get() = "Tank"
}

interface WheeledVehicle: Vehicle {
    val numberOfWheels: Int
    var wheelSize: Double
}

class Bike: WheeledVehicle {
    var peddling = false
    var brakesApplied = false

    override val numberOfWheels: Int = 2
    override var wheelSize: Double = 622.0

    override fun accelerate() {
        peddling = true
        brakesApplied = false
    }

    override fun stop() {
        peddling = false
        brakesApplied = true
    }
}

interface Wheeled {
    val numberOfWheels: Int
    val wheelSize: Double
}

//class Tricycle: Wheeled, Vehicle {
//    // Implement both vehicle and wheeled
//}

interface SizedVehicle {
    var lenght: Int
}

class Boat: SizedVehicle, Comparable<Boat> {
    override var lenght: Int = 0

    override fun compareTo(other: Boat): Int {
        return when {
            lenght > other.lenght -> 1
            lenght == other.lenght -> 0
            else -> -1
        }
    }
}