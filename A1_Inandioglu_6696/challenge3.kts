fun shippingCost(
    weightKg: Double,
    express: Boolean = false,
    memberDiscount: Boolean = false,
): Double {
    var cost = 4.99 + (1.20 * weightKg)

    if (express) {
        cost *= 1.75
    }

    if (memberDiscount) {
        cost *= 0.90
    }

    return cost
}

fun main() {
    val weightKg = 3.5

    println("Standard:                \$${"%.2f".format(shippingCost(weightKg))}")
    println("Express:                 \$${"%.2f".format(shippingCost(weightKg, true))}")
    println("Standard (member):       \$${"%.2f".format(shippingCost(weightKg, memberDiscount = true))}")
    println(
        "Express (member, named): \$${
            "%.2f".format(
                shippingCost(
                    weightKg = weightKg,
                    express = true,
                    memberDiscount = true,
                ),
            )
        }",
    )
}
