fun gradeReport(
    studentName: String?,
    score: Int,
    maxScore: Int = 100,
    showDetails: Boolean = false,
): String {
    val name = studentName?.trim()?.takeIf { it.isNotEmpty() } ?: "Unknown Student"
    val percentage = score * 100 / maxScore
    val letterGrade =
        if (percentage >= 90) {
            "A"
        } else if (percentage >= 80) {
            "B"
        } else if (percentage >= 70) {
            "C"
        } else if (percentage >= 60) {
            "D"
        } else {
            "F"
        }
    val details = if (showDetails) " [$score/$maxScore]" else ""

    return "$name: $percentage% — $letterGrade$details"
}

fun main() {
    println(gradeReport("Alice", 88))
    println(gradeReport("  Bob  ", 95, showDetails = true))
    println(gradeReport(null, 72))
    println(gradeReport("Dana", 55, maxScore = 60, showDetails = true))
}
