package com.example.podnaloga01

import kotlin.random.Random

class Helpers {

    companion object{
        fun generatePlayerRank(n: Int): Int{

            return (0..n).random()
        }
    }

}