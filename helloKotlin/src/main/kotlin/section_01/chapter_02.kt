package section_01

import kotlin.math.*

/*
Key points:
- Computers, at their most fundamental level, perform simple mathematics.
- A programming language allows you to write code, which the compiler converts into instructions that the CPU can
execute. Kotlin code on the JVM is first converted to bytecode.
- Computers operate on numbers in base 2 form, otherwise known as binary.
- Code comments are denoted by a line starting with // or multiple lines bookended with /* and */.
- Code comments can be used to document your code.
- You can use println to write things to the console area.
- The arithmetic operators are:
    Add: +
    Subtract: -
    Multiply: *
    Divide: /
    Remainder: %
- Constants and variables give names to data.
- Once you've declared a constant, you can't change its data, but you can change a variable's data at any time.
- Always give variables and constants meaningful names to save yourself and your colleagues headaches later.
- Operators to perform arithmetic and then assign back to the variable.
    Add and assign: +=
    Subtract and assign: -=
    Multiply and assign: *=
    Divide and assign: /=
 */

// compile-time constant
const val reallyConstant: Int = 42

fun main() {
    /* --- Getting started with Kotlin --- */
    /* -- Code comments -- */
    // This is a comment. It is not executed.

    // This is also a comment.
    // Over multiple lines.

    /* This is also a comment.
       Over many..
       many..
       many lines. */

    /* This is a comment
        /* And inside it
        is
        another comment.
        */

        Back to the first.
     */

    /* --- Printing out --- */
    println("Hello, Kotlin Apprentice reader!")

    /* --- Arithmetic operations --- */
    /* -- Simple operations -- */
    println(2 + 6)
    println(10 - 2)
    println(2 * 4)
    println(24 / 3)

    /* -- Decimal numbers -- */
    println(22.0 / 7.0)

    /* -- The remainder operation -- */
    println(28 % 10)
    println("%.0f".format(28.0 % 10.0))

    /* -- Shift operations -- */
    // Shift left: shl
    println(1 shl 3)

    // Shift right: shr
    println(32 shr 2)

    /* -- Order of operations -- */
    println(((8000 / (5 * 10)) - 32) shr (29 % 5))
    println(350 / 5 + 2)

    /* --- Math functions --- */
    println(sin(45 * PI / 180))
    println(cos(135 * PI / 180))
    println(sqrt(2.0))
    println(max(5, 10))
    println(min(-5, -10))
    println(max(sqrt(2.0), PI / 2))

    /* --- Naming data --- */
    /* -- Constants -- */
    val number: Int = 10
    println(number)

    val pi: Double = 3.14
    println(pi)

    println(reallyConstant)

    /* -- Variables -- */
    var variableNumber: Int = 42
    println(variableNumber)
    variableNumber = 0
    println(variableNumber)
    variableNumber = 1_000_000
    println(variableNumber)

    /* -- Using meaningful names -- */
    /*
        - Start with a lowercase letter.
        - If the name is made up of multiple words, join them together and start every word other than the first word
        with an uppercase letter.
        - If one of these words is an abbreviation, follow the same pattern as if it was a word (E.g.,sourceUrl and
        urlDescription)
     */

    /* --- Increment and decrement --- */
    var counter: Int = 0
    println(counter)

    counter += 1
    println(counter)

    counter -= 1
    println(counter)

    counter = 10
    println(counter)

    counter *= 3
    println(counter)

    counter /= 2
    println(counter)

    
}