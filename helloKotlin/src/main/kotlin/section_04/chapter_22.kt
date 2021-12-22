package section_04

/*
Key points:
- To use overloaded operators, it's necessary to follow the specific conventions for the operator.
- Conventions manage multiple features in Kotlin, such as operator overloading, infix functions and delegated
properties.
- Operators should always behave predictably; don't overload operators in a way that makes their behavior unclear for
other developers who might use or read your code.
 */

fun main() {
    val company = Company("MyOwnCompany")

    val developmentDepartment = Department("Development")
    val qaDepartment = Department("Quality Assurance")
    val hrDepartment = Department("Human Resources")

    var Julia = Employee(company, "Julia", 100_000)
    var John = Employee(company, "John", 86_000)
    var Peter = Employee(company, "Peter", 100_000)

    var Sandra = Employee(company, "Sandra", 75_000)
    var Thomas = Employee(company, "Thomas", 73_000)
    var Alice = Employee(company, "Alice", 70_000)

    var Bernadette = Employee(company, "Bernadette", 66_000)
    var Mark = Employee(company, "Mark", 66_000)

    ++Julia
    --Peter
    Mark += 2500
    Alice -= 2000

    company += developmentDepartment
    company += qaDepartment
    company += hrDepartment

    developmentDepartment += Julia
    developmentDepartment += John
    developmentDepartment += Peter

    qaDepartment += Sandra
    qaDepartment += Thomas
    qaDepartment += Alice

    hrDepartment += Bernadette
    hrDepartment += Mark

    qaDepartment -= Thomas

    //qaDepartment[1] = Thomas

    if (Thomas !in qaDepartment) {
        println("${Thomas.name} no longer works here")
    }

    println((Alice..Mark).joinToString { it.name })

}