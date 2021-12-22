package section_04

class Company(val name: String) {
    val allEmployees: List<Employee>
        get() = arrayListOf<Employee>().apply {
            departments.forEach { addAll(it.employees) }
            sort()
        }
    private val departments: ArrayList<Department> = arrayListOf()

    operator fun plusAssign(department: Department) {
        departments.add(department)
    }

    operator fun minusAssign(department: Department) {
        departments.remove(department)
    }
}