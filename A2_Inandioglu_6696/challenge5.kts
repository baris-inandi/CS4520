import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun fetchCurrentWeather(): String {
    delay(1000)
    return "Sunny, 22°C"
}

suspend fun fetchForecastSummary(): String {
    delay(1000)
    return "Rain expected tomorrow"
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val currentWeather = async { fetchCurrentWeather() }
    val forecastSummary = async { fetchForecastSummary() }

    println(currentWeather.await())
    println(forecastSummary.await())
    println("Elapsed time: ${System.currentTimeMillis() - startTime} ms")
}
