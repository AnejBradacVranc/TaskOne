package com.example.podnaloga01

class Location( val address: String, val country: String) {//val ne moremo spremeniti po tem ko je vrednost dodeljena, private drugim razredom onemogoci da bi dostopali do te spremenljivke
    override fun toString(): String {
        return "Naslov: $address, Drzava: $country"
    }
}