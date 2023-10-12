package com.example.podnaloga01

class Warehouse(val products : List<Product>, val location: Location, val maxSize: Int) : Sizable{

    init {
        if(products.size > maxSize)
            throw WarehouseInsufficientCapacityException("Prevec izdelkov za to skladisce!")
    }

    override fun size(): Int {
        return products.size
    }
    override fun toString(): String {
        return "SKLADISCE \n ${products.toString()} \n ${location.toString()}"
    }
}