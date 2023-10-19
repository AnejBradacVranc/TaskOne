package com.example.podnaloga01

import io.github.serpro69.kfaker.Faker

class Main {

}

fun main(args: Array<String>) {

    val faker = Faker()
    val helpers = Helpers()
    val players = List(20) {
        Player(
            faker.money.amount(IntRange(100, 200), true, ".", ","),
            faker.name.name(),
            faker.name.lastName(),
            helpers.generatePlayerRank(100)
        )
    }

    val sortedProducts = players.sorted()
    val tableTennisClub: TableTennisClub
    try {
        tableTennisClub =
            TableTennisClub(
                sortedProducts,
                Location(faker.address.streetAddress(), faker.address.country()),
                15
            )

        println(tableTennisClub)

        println("Stevilo igralcev v klubu: ${tableTennisClub.size()}")

    } catch (e: TTClubInsufficientCapacityException) {
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