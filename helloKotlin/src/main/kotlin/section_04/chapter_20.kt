package section_04

import java.lang.Exception

/*
Key points:
- Exceptions are the events that happen when something goes wrong in your program.
- Extend the Exception class or its subclasses to create custom exceptions.
- Throw an exception using the throw keyword.
- Do not catch the base class Exception, use the most specific exception class you can
- Create custom exception for uncommon cases to differentiate them.
- When handling exceptions, place the code that should be executed whether an exception occurs or not in the finally
block.
- All exceptions in Kotlin are unchecked.
- Don't ignore exceptions.
- Try-catch is an exception.
 */

fun main() {
    // someFunction()

    /*--- Throwing exceptions ---*/
    val spaceCraft = SpaceCraft()
    SpacePort.investigateSpace(spaceCraft)

    /*--- Handling exceptions ---*/
    /*--- Creating custom exceptions ---*/
    /*--- Difference between Java and Kotlin exceptions ---*/
    /*-- Checked exception --*/
    /*-- try as an exception --*/
}

fun someFunction() {
    anotherFunction()
}

fun anotherFunction() {
    oneMoreFunction()
}

fun oneMoreFunction() {
    throw Exception("Something went wrong")
}

class SpaceCraft {
    private var isConnectionAvailable: Boolean = false
    private var isEngineInOrder: Boolean = false
    private var fuel: Int = 0
    var isInSpace: Boolean = false

    fun launch() {
        if (fuel < 5) {
            throw OutOfFuelException()
        }

        if (!isEngineInOrder) {
            throw BrokenEngineException()
        }

        if (!isConnectionAvailable) {
            throw SpaceToEarthConnectionFailedException()
        }

        sendMessageToEarth("Trying to launch...")
        fuel -= 5
        sendMessageToEarth("I'm in space!")
        sendMessageToEarth("I've found some extraterrestrials")
        isInSpace = true
    }

    fun sendMessageToEarth(message: String) {
        println("Spacecraft to Earth: $message")
    }

    fun refuel() {
        fuel += 5
        sendMessageToEarth("The fuel tank is filled.")
    }

    fun repairEngine() {
        isEngineInOrder = true
        sendMessageToEarth("The engine is in order.")
    }

    fun fixConnection() {
        isConnectionAvailable = true
        sendMessageToEarth("Hello Earth! Can you hear me?")
        sendMessageToEarth("Connection is established.")
    }

    fun land() {
        sendMessageToEarth("Landing...")
        isInSpace = false
    }
}

class OutOfFuelException: Exception("Out of fuel. Can't take off.")
class BrokenEngineException: Exception("The engine is broken. Can't take off.")
class SpaceToEarthConnectionFailedException: Exception("No connection with Earth. Can't take off.")

object SpacePort {
    fun investigateSpace(spaceCraft: SpaceCraft) {
        try {
            spaceCraft.launch()
        } catch (exception: OutOfFuelException) {
            spaceCraft.sendMessageToEarth(exception.localizedMessage)
            spaceCraft.refuel()
        } catch (exception: BrokenEngineException) {
            spaceCraft.sendMessageToEarth(exception.localizedMessage)
            spaceCraft.repairEngine()
        } catch (exception: SpaceToEarthConnectionFailedException) {
            spaceCraft.sendMessageToEarth(exception.localizedMessage)
            spaceCraft.fixConnection()
        } finally {
            if (spaceCraft.isInSpace) {
                spaceCraft.land()
            } else {
                investigateSpace(spaceCraft)
            }
        }
    }
}