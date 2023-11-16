package com.example.tableTennis

import io.github.serpro69.kfaker.Faker

class Main {

}

fun main(args: Array<String>) {

    val faker = Faker()

    val players = PlayersGenerator.generate(100)

    val sortedPlayers = players.sorted()

    val tableTennisClub: TableTennisClub
    try {
        tableTennisClub =
            TableTennisClub(
                Location(faker.address.streetAddress(), faker.address.country()),
                100
            )

        tableTennisClub.addPlayers(sortedPlayers.toMutableList())

        val wantedString = "se";

        //println(tableTennisClub)
        //println("Stevilo igralcev v klubu: ${tableTennisClub.size()}")

        println("Objekti, ki vsebujejo niz ${wantedString}:\n ${tableTennisClub.findByString(wantedString)}")

        println("Objekti, ki ne vsebujejo niza ${wantedString}:\n ${tableTennisClub.doesNotContainString(wantedString)}")

        println("Povprecna lokalna uvrstitev:\n ${tableTennisClub.averageLocalRanking()}")

        println("Objekti, z imenom daljsim od 5:\n ${tableTennisClub.nameLongerThan(5,10)}")

        println("Pojavitve niza:\n ${tableTennisClub.countOccurencies(wantedString)}")

    } catch (e: TTClubInsufficientCapacityException) {
        println(e.message)
    } //try catch block prej



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