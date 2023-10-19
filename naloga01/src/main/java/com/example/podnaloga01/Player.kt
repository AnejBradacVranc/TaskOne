package com.example.podnaloga01

class Player(val contractPrice: String, val name: String, val surename: String, val rank: Int): Comparable<Player>{

    override fun compareTo(other: Player): Int = rank compareTo other.rank
    override fun toString(): String = "Contract price: $contractPrice, Name: $name, Surename: $surename, Ranking: $rank"

}