package com.example.podnaloga01

import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

class PlayersGenerator {
    companion object {
        fun generate(n: Int): List<Player> {
            val faker = Faker()
            val players = mutableListOf<Player>()
            val random = Random.Default;

            for (i in 1..n) {
                val type = random.nextBoolean()

                players.add(
                    if (type) {
                        Player(
                            faker.money.amount(IntRange(100, 200), true, ".", ","),
                            faker.name.name(),
                            faker.name.lastName(),
                            Helpers.generatePlayerRank(n)
                        )
                    }
                    else {
                        CompetitivePlayer(
                            faker.money.amount(IntRange(100, 200), true, ".", ","),
                            faker.name.name(),
                            faker.name.lastName(),
                            Helpers.generatePlayerRank(n),
                            Helpers.generatePlayerRank(n),
                            faker.money.amount(IntRange(100, 200), true, ".", ",")
                        )
                    }
                )
            }

            return players
        }
    }
}