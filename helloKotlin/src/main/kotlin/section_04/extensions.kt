package section_04

import java.util.*

fun Random.randomStrength(): Int {
    return nextInt(100) + 10
}

fun Random.randomDamage(strength: Int): Int {
    return (strength * 0.1 + nextInt(10)).toInt()
}

fun Random.randomBlock(): Boolean {
    return nextBoolean()
}