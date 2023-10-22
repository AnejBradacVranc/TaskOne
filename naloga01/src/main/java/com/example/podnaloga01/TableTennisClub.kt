package com.example.podnaloga01

class TableTennisClub( val location: Location, val maxSize: Int,var players : MutableList<Player> = mutableListOf()) : Sizable{
    init {
        if(players.size > maxSize)
            throw TTClubInsufficientCapacityException("V klubu je prevec igralcev za njegovo kapaciteto!")
    }

    fun addPlayers(newPlayers: MutableList<Player>){
        players += newPlayers;
    }

    override fun size(): Int {
        return players.size
    }
    override fun toString(): String {
        return "KLUB \n ${players.toString()} \n ${location.toString()}"
    }

    fun findByString(string: String) : List<Player>{
        val cpy = players.toMutableList();
        cpy.removeIf{ !("${it.name} ${it.surname}").contains(string)}
        return cpy
    }

    fun doesNotContainString(string: String) : List<Player>{
        val cpy = players.toMutableList();
        cpy.removeIf{it.name.contains(string) || it.surname.contains(string)}
        return cpy
    }

    fun averageLocalRanking(): Int{
        if(players.isNotEmpty()) return players.sumOf {it.localRank }/players.size
        return -1
    }

    fun nameLongerThan(len: Int, n: Int): List <Player>{
        return players.filter { it.name.length>len }.take(n)
    }

    fun countOccurencies(string: String): Int {

        return players.filter { it.name == string || it.surname == string }.size
    }




}