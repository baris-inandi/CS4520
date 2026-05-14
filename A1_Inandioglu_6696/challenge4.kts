fun ticketPrice(age: Int): Double {
    val price = if (age < 5) {
        0.0
    } else if (age <= 12) {
        8.50
    } else if (age <= 17) {
        11.00
    } else if (age <= 64) {
        14.50
    } else {
        10.00
    }

    return price
}

fun ticketSummary(name: String, age: Int): String {
    val price = ticketPrice(age)
    val priceText = if (price == 0.0) "FREE" else "\$${"%.2f".format(price)}"

    return "$name (age $age): $priceText"
}

fun main() {
    println(ticketSummary("Lily", 3))
    println(ticketSummary("Sam", 10))
    println(ticketSummary("Jordan", 16))
    println(ticketSummary("Morgan", 34))
    println(ticketSummary("Pat", 70))
}
