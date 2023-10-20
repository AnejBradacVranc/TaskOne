package com.example.podnaloga01

open class Player(val membershipPrice: String, val name: String, val surname: String, val rank: Int): Comparable<Player>{

    override fun compareTo(other: Player): Int = rank compareTo other.rank
    override fun toString(): String = "Membership price: $membershipPrice, Name: $name, Surename: $surname, Ranking: $rank"

}