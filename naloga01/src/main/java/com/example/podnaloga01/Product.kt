package com.example.podnaloga01

class Product(val price: String, val id: Long, val name: String, val weight:Double): Comparable<Product>{

    override fun compareTo(other: Product): Int = weight compareTo other.weight
    override fun toString(): String = "Price: $price, ID: $id, Name: $name, Weight: $weight"

}