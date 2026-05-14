fun main() {
    val studentName = "Alice Smith"
    var age: Int = 21
    val studentId: String = "NEU-12345"
    val department = "Computer Science"

    println("=== Student Card ===")
    println("Name: $studentName")
    println("Age: $age")
    println("ID: $studentId")
    println("Department: $department")

    age += 1
    println("Happy birthday, $studentName! You are now ${age + 0}.")
}
