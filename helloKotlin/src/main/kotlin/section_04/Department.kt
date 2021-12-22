package section_04

class Department(
    val name: String = "Department"
): Iterable<Employee> {
    val employees: ArrayList<Employee> = arrayListOf()

    operator fun plusAssign(employee: Employee) {
        employees.add(employee)
        println("${employee.name} hired to $name department")
    }

    operator fun minusAssign(employee: Employee) {
        if (employees.contains(employee)) {
            employees.remove(employee)
            println("${employee.name} fired from $name department")
        }
    }

    operator fun get(index: Int): Employee? {
        return if (index < employees.size) {
            employees[index]
        } else {
            null
        }
    }

    operator fun set(index: Int, employee: Employee) {
        if (index < employees.size) {
            employees[index] = employee
        }
    }

    operator fun contains(employee: Employee) = employees.contains(employee)

    override fun iterator() = employees.iterator()
}