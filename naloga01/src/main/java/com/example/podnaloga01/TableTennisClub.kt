package com.example.podnaloga01

class TableTennisClub(val players : List<Player>, val location: Location, val maxSize: Int) : Sizable{

    init {
        if(players.size > maxSize)
            throw WarehouseInsufficientCapacityException("Prevec izdelkov za to skladisce!")
    }

    override fun size(): Int {
        return players.size
    }
    override fun toString(): String {
        return "SKLADISCE \n ${players.toString()} \n ${location.toString()}"
    }
}