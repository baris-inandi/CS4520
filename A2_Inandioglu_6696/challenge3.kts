fun main() {
    val temps = listOf(15.5, 22.0, 18.0, 25.5, 30.0, 12.5, 28.0)

    val hottestTemps = temps
        .filter { it >= 20.0 }
        .map { it * 9.0 / 5.0 + 32.0 }
        .sortedByDescending { it }
        .take(2)

    println(hottestTemps)
}
