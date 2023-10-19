package com.example.podnaloga01

class TableTennisClub(val players : List<Player>, val location: Location, val maxSize: Int) : Sizable{

    init {
        if(players.size > maxSize)
            throw TTClubInsufficientCapacityException("V klubu je prevec igralcev za njegovo kapaciteto!")
    }

    override fun size(): Int {
        return players.size
    }
    override fun toString(): String {
        return "KLUB \n ${players.toString()} \n ${location.toString()}"
    }
}