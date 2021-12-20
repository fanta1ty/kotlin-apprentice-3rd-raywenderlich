package section_04

/*
Key points:
- Functional programming uses first-class functions, which can be passed as arguments, returned or assigned to variables
- A higher-order functions is a function that receives another function as a parameter and/or returns one.
- A lambda is a function literal defined in curly brackets, and can be invoked, passed to a function, returned or
assigned to a variable.
- When you create a lambda, an implicit class is created that implements a FunctionN interface, where N is number of
parameters that the lambda receives.
- Kotlin lambdas act as closures, with access variables defined in the outer scope of the lambda.
- Extension functions implicitly receive an instance of the extended class as the first parameter.
- Lambdas with receivers are similar to extension functions.
- Mark a lambda that shouldn't support a non-local return with the crossinline keyword.
- Use the tailrec keyword to optimize tail-recursive functions.
- Use the inline keyword to replace a function invocation with its body.
- If a function is a member function or extension function, and it receives only one argument, you can mark it with an
infix keyword and call it without the dot operator or parentheses.
- Use sequences to create lazily evaluated collections.
 */

fun main() {
    val firstRobot = Robot("Experimental Space Navigation Droid")
    val secondRobot = Robot("Extra-Terrestrial Air Safety Droid")

    Battlefield.beginBattle(firstRobot, secondRobot, ::onBattleEnded)

    /*--- First-class and higher-order functions ---*/
    /*-- Function types --*/
    /*-- Passing a function as an argument --*/
    /*-- Returning functions --*/
    /*--- Lambdas ---*/
    val onBattleEnded = { winner: Robot -> winner.report("Win!") }
    Battlefield.beginBattle(firstRobot, secondRobot, onBattleEnded)
    Battlefield.beginBattle(firstRobot, secondRobot) {
        this.report("Win!")
    }

    /*-- Closures --*/
    /*--- Extension functions ---*/
    /*--- Lambdas with receivers ---*/
    /*--- Anonymous functions ---*/
    /*--- Returning from lambdas ---*/
    /*--- Inline functions ---*/
    /*--- noinline ---*/
    /*--- crossinline ---*/
    /*--- Tail recursive functions ---*/
    /*--- Collections standard library ---*/
    val participants = arrayListOf<Robot>(
        Robot("Extra-Terrestrial Neutralization Bot"),
        Robot("Generic Evasion Droid"),
        Robot("Self-Reliant War Management Device"),
        Robot("Advanced Nullification Android"),
        Robot("Rational Network Defense Droid"),
        Robot("Motorized Shepherd Cyborg"),
        Robot("Reactive Algorithm Entity"),
        Robot("Ultimate Safety Guard Golem"),
        Robot("Nuclear Processor Machine"),
        Robot("Preliminary Space Navigation Machine")
    )

    val topCategory = participants
        .filter { it.strength > 80 }
        .take(3)
        .sortedBy { it.name }
    println("Top Category: $topCategory")

    /*--- Infix notation ---*/
    /*--- Sequence ---*/
    val random = java.util.Random()
    val sequence = generateSequence {
        random.nextInt(100)
    }

    sequence
        .take(15)
        .sorted()
        .forEach { println(it) }

    val factorial = generateSequence(1 to 1) {
        it.first + 1 to it.second * (it.first + 1)
    }
    println(factorial.take(10).map { it.second }.last())
}

fun onBattleEnded(winner: Robot) {
    winner.report("Win!")
}