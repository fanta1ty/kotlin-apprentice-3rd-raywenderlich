package section_04

data class Employee(
    val company: Company,
    val name: String,
    var salary: Int
    ): Comparable<Employee> {

    operator fun inc(): Employee {
        salary += 5000
        println("$name got a raise to $$salary")
        return this
    }

    operator fun dec(): Employee {
        salary -= 5000
        println("$name's salary decreased to $$salary")
        return this
    }

    operator fun plusAssign(increaseSalary: Int) {
        salary += increaseSalary
        println("$name got a raise to $$salary")
    }

    operator fun minusAssign(decreaseSalary: Int) {
        salary -= decreaseSalary
        println("$name's salary decrease to $$salary")
    }

    override fun compareTo(other: Employee): Int {
        return when (other) {
            this -> 0
            else -> name.compareTo(other.name)
        }
    }

    operator fun rangeTo(other: Employee): List<Employee> {
        val currentIndex = company.allEmployees.indexOf(this)
        val otherIndex = company.allEmployees.indexOf(other)

        if (currentIndex >= otherIndex) {
            return emptyList()
        }

        return company.allEmployees.slice(currentIndex..otherIndex)
    }
}