package com.example.tableTennis

import java.util.UUID

open class Player(
    val membershipPrice: String,
    val name: String,
    val surname: String,
    val localRank: Int,
    var id:String = UUID.randomUUID().toString().replace("-","")
): Comparable<Player>{


    override fun compareTo(other: Player): Int = localRank compareTo other.localRank
    override fun toString(): String = "\nMembership price: $membershipPrice\nName: $name\nSurename: $surname\nRanking: $localRank\nID: $id"

}