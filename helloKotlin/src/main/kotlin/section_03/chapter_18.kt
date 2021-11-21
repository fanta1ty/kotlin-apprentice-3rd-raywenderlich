package section_03

/*
Key points:
- Generics is a gargantuan topic, so review some of the most important things to remember about them in Kotlin:
    + Generics allow you to create classes or interfaces that operate on a type that is not known when your code for
    that class or interface is written.
    + Generic programming can allow you to centralize pieces of functionality in a highly reusable and easily debuggable
    fashion.
    + Type erasure means that, within a class or interface that takes a generic type, you won't have any information
    about that type at compile time unless you annotate the type with reified and inline the function.
    + Allowing only in or out variance of a generic type allows you to restrict whether a generic type can be passed in
    to extensions or be returned from subclasses or other functions on a particular generic interface or class. This, in
    turn, allows both you and the compiler to make assumptions about how generic types relate to each other.
 */

fun main() {
    /*--- Anatomy of standard library generic types ---*/
    /*-- Lists --*/
    val names = listOf("Bob", "Carol", "Ted", "Alice")
    println("Name: $names")
    val firstName = names.first()
    println(firstName)

    val things = mutableListOf<Any>(1, 2)
    things.add("Steve")
    println("Things: ${things.toBulletedList()}")

    /*-- Maps --*/
    val map = mapOf(
        Pair("one", 1),
        Pair("two", "II"),
        Pair("three", 3.0f)
    )
    val valuesForKeyWithE = map.keys
        .filter { it.contains("e") }
        .map { "Value for $it: ${map[it]}" }
    println(valuesForKeyWithE.toBulletedList())

    /*--- Extension functions on types with generic constraints ---*/
    println("Name: ${names.toBulletedList()}")

    /*--- Creating your own generic constraints ---*/
    val cheapThings = listOf(
        CheapThing("Cinder Block"),
        CheapThing("Box of old books"),
        CheapThing("Ugly old couch")
    )
    val cheapMover = Mover(cheapThings)
    cheapMover.moveEverythingToTruck(null)
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-screen Television")
    val breakableThings = listOf(
        television,
        BreakableThing("Mirror"),
        BreakableThing("Guitar")
    )
    val expensiveMover = Mover(breakableThings)
    expensiveMover.moveEverythingToTruck(CardboardBox())
    television.smash()
    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()

    /*-- Interaces --*/
    /*--- Generic interfaces ---*/
    /*--- Type erasure ---*/
    /*-- Star projection --*/
    /*-- Reified type parameters --*/
    /*--- Generic type variance(a.k.a, in and out declarations) ---*/
    val ints = listOf(1, 2, 3)
    val numbers: List<Number> = ints

    fun compare(comparator: Comparable<Number>) {
        val int: Int = 1
        comparator.compareTo(int)
        val float: Float = 1.0f
        comparator.compareTo(float)

        val intComparable: Comparable<Int> = comparator
        intComparable.compareTo(int)
    }
}

fun <T> List<T>.toBulletedList(): String {
    val separator ="\n - "
    return this.map { "$it" }
        .joinToString(separator, separator, "\n")
}

interface Container<T> {
    fun canAddAnotherItem(): Boolean
    fun addItem(item: T)
    fun canRemoveAnotherItem(): Boolean
    fun removeItem(): T
    fun getAnother(): Container<T>
    fun contents(): List<T>
}
 class CardboardBox: Container<BreakableThing> {
     private var items = mutableListOf<BreakableThing>()

     override fun contents(): List<BreakableThing> {
         return items.toList()
     }

     override fun canAddAnotherItem(): Boolean {
         return items.count() < 2
     }

     override fun addItem(item: BreakableThing) {
         items.add(item)
     }

     override fun canRemoveAnotherItem(): Boolean {
         return items.count() > 0
     }

     override fun removeItem(): BreakableThing {
         val lastItem = items.last()
         items.remove(lastItem)
         return lastItem
     }

     override fun getAnother(): Container<BreakableThing> {
         return CardboardBox()
     }
 }

interface Checkable {
    fun checkIsOK(): Boolean
}

class Mover<T: Checkable>(
    thingsToMove: List<T>,
    val truckHeightInInches: Int = (12 * 12)
) {
    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<Any>()
    private var thingsInNewPlace = mutableListOf<T>()
    private var thingsWhichFailedCheck = mutableListOf<T>()

    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }

    fun moveEverythingToTruck(startingContainer: Container<T>?) {
        var currentContainer = startingContainer
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)

            if (item.checkIsOK()) {
                if (currentContainer != null) {
                    if (!currentContainer.canAddAnotherItem()) {
                        moveContainerToTruck(currentContainer)
                        currentContainer = currentContainer.getAnother()
                    }

                    currentContainer.addItem(item)
                    println("Packed your $item!")
                } else {
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                }
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck :[")
            }
        }
        currentContainer?.let { moveContainerToTruck(it) }
    }

    private fun moveContainerToTruck(container: Container<T>) {
        thingsInTruck.add(container)
        println("Moved a container with your ${container.contents().toBulletedList()} to the truck")
    }

    private fun tryToMoveItemIntoNewPlace(item: T) {
        if (item.checkIsOK()) {
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        } else {
            thingsWhichFailedCheck.add(item)
            println("Could not move your $item into your new place :[")
        }
    }

    fun moveEverythingIntoNewPlace() {
        val containers = thingsInTruck.filterIsInstance<Container<T>>()
        for (container in containers) {
            thingsInTruck.remove(container)

            while (container.canRemoveAnotherItem()) {
                val itemInContainer = container.removeItem()
                println("Unpacked your $itemInContainer!")
                tryToMoveItemIntoNewPlace(itemInContainer)
            }
        }

        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0) as? T

            if (item != null) {
                tryToMoveItemIntoNewPlace(item)
            } else {
                println("Something in the truck was not of the expected generic type: $item")
            }
        }
    }

    fun finishMove() {
        println("OK, we finished! We were able to move your: ${thingsInNewPlace.toBulletedList()}")

        if (thingsWhichFailedCheck.isNotEmpty()) {
            println("But we need to talk about your: ${thingsWhichFailedCheck.toBulletedList()}")
        }
    }
}

class CheapThing(val name: String): Checkable {
    override fun checkIsOK(): Boolean = true

    override fun toString(): String {
        return name
    }
}

class BreakableThing(
    val name: String,
    var isBroken: Boolean = false
): Checkable {
    override fun checkIsOK(): Boolean {
        return !isBroken
    }

    fun smash() {
        isBroken = true
    }

    override fun toString(): String {
        return name
    }
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}
fun compare(comparator: Comparable<Number>) {
    val int: Int = 1
    comparator.compareTo(int)
    val float: Float = 1.0f
    comparator.compareTo(float)
    val intComparable: Comparable<Int> = comparator
}