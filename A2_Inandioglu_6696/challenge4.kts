import java.util.Locale

fun Double.formatTemp(unit: String): String = String.format(Locale.US, "%.1f%s", this, unit)

fun main() {
    val city = "Boston"
    val condition = "Sunny"
    val tempC = 22.5

    val summary = StringBuilder().apply {
        appendLine(city)
        appendLine(condition)
        appendLine(tempC.formatTemp("°C"))
    }

    summary.toString().let {
        println("***\n$it***")
    }
}
