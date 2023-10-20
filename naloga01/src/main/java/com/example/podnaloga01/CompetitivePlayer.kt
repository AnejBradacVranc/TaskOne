package com.example.podnaloga01

class CompetitivePlayer(membershipPrice: String, name: String, surname: String, rank: Int, val worldRank: Int, val contractPrice: String ): Player(membershipPrice,name,surname,rank) {
    override fun toString(): String {
        return "WORLD RANKED COMPETITIVE PLAYER ${super.toString()} WR: ${worldRank}, Contract price: ${contractPrice}";
    }
}