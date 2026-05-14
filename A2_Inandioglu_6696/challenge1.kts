data class WeatherForecast(
    val city: String,
    val condition: String,
    val tempC: Double
)

fun main() {
    val forecast = WeatherForecast("Boston", "Sunny", 22.0)
    val updatedForecast = forecast.copy(condition = "Rain", tempC = 15.5)

    println(forecast)
    println(updatedForecast)
}
