sealed class WeatherAlert {
    data class Tornado(val severity: Int) : WeatherAlert()
    data class Flood(val riverName: String) : WeatherAlert()
    object Clear : WeatherAlert()
}

fun handleAlert(alert: WeatherAlert): String = when (alert) {
    is WeatherAlert.Tornado -> "TORNADO WARNING! Level ${alert.severity}"
    is WeatherAlert.Flood -> "Flood watch for ${alert.riverName} river."
    WeatherAlert.Clear -> "No active alerts."
}

fun main() {
    val alerts = listOf(
        WeatherAlert.Tornado(5),
        WeatherAlert.Flood("Charles"),
        WeatherAlert.Clear
    )

    for (alert in alerts) {
        println(handleAlert(alert))
    }
}
