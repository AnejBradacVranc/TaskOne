package com.example.tableTennis

open class Player(val membershipPrice: String, val name: String, val surname: String, val localRank: Int): Comparable<Player>{

    override fun compareTo(other: Player): Int = localRank compareTo other.localRank
    override fun toString(): String = "Membership price: $membershipPrice, Name: $name, Surename: $surname, Ranking: $localRank"

}