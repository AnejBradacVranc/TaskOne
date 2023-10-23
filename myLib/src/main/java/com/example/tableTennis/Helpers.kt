package com.example.tableTennis

class Helpers {

    companion object{
        fun generatePlayerRank(n: Int): Int{

            return (0..n).random()
        }
    }

}