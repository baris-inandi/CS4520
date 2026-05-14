fun displayName(firstName: String?, lastName: String?): String {
    val first = firstName?.trim()?.takeIf { it.isNotEmpty() }
    val last = lastName?.trim()?.takeIf { it.isNotEmpty() }

    return listOfNotNull(first, last)
        .joinToString(" ")
        .takeIf { it.isNotEmpty() }
        ?: "Anonymous"
}

fun main() {
    println(displayName("Alice", "Smith"))
    println(displayName("Alice", null))
    println(displayName(null, "Smith"))
    println(displayName(null, "   "))
}
