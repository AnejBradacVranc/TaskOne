package com.example.podnaloga01

import io.github.serpro69.kfaker.Faker

class Main {

}

fun main(args: Array<String>) {

    val faker = Faker()
    val helpers = Helpers()
    val products = List(20) {
        Product(
            faker.money.amount(IntRange(100, 200), true, ".", ","),
            faker.barcode.ean13().toLong(),
            faker.commerce.productName(),
            helpers.generateProductWeights(100)
        )
    }

    val sortedProducts = products.sorted()
    val warehouse: Warehouse
    try {
        warehouse =
            Warehouse(
                sortedProducts,
                Location(faker.address.streetAddress(), faker.address.country()),
                15
            )

        println(warehouse)

        println("Kolicina izdelkov v skladiscu: ${warehouse.size()}")

    } catch (e: WarehouseInsufficientCapacityException) {
        println(e.message)
    }


    //1. izjema
    try {
        val list = listOf(2, 3, 4, 6, 2)
        println(list[20])
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Polje dostopaš izven mej!")
    }

    //2. izjema
    try {
        val res = 115 / 0
        println(res)
    } catch (e: ArithmeticException) {
        println("Ne gre deliti z 0!")
    }

    //3. izjema
    try {

        val num = "String".toInt()
        println(num)
    } catch (e: NumberFormatException) {
        println("Napaka pri formatiranju: $e")
    }


}