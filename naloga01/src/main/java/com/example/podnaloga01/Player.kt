package com.example.podnaloga01

class Player(val price: String, val id: Long, val name: String, val rank: Int): Comparable<Player>{

    override fun compareTo(other: Player): Int = rank compareTo other.rank
    override fun toString(): String = "Price: $price, ID: $id, Name: $name, Weight: $rank"

}