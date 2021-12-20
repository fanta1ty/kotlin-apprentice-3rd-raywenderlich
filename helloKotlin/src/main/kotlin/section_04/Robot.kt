package section_04

import java.util.*

class Robot(val name: String) {
    var strength: Int = 0
    var isAlive: Boolean = true

    private var health: Int = 100
    private var random: Random = Random()

    init {
        strength = random.randomStrength()
        report("Create (strength $strength)")
    }

    fun report(message: String) {
        println("$name: \t$message")
    }

    infix fun attack(robot: Robot) {
        val damage = random.randomDamage(strength)
        robot.damage(damage)
    }

    private fun damage(damage: Int) {
        val blocked = random.randomBlock()

        if (blocked) {
            report("Blocked attack")
            return
        }

        health -= damage
        report("Damage -$damage, health $health")

        if (health <= 0) {
            isAlive = false
        }
    }
}