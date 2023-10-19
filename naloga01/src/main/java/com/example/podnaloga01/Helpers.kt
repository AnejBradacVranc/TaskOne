package com.example.podnaloga01

import kotlin.random.Random

class Helpers {

    fun generatePlayerRank(n: Int): Int{

        val random = Random.Default

        return random.nextInt()* 100;
    }
}