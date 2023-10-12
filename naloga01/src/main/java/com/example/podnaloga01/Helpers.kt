package com.example.podnaloga01

import kotlin.random.Random

class Helpers {

    fun generateProductWeights( n: Int):Double{

        val random = Random.Default

        return    random.nextDouble()* 100.0
    }
}