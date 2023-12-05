package com.example.taskone


import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtils {

    companion object{
        fun saveID(sharedPref : SharedPreferences ,key:String, value:String) {
            with (sharedPref.edit()) {
                putString(key, value)
                apply()
            }
        }
        fun saveID(sharedPref : SharedPreferences ,key:String, value:Boolean) {
            with (sharedPref.edit()) {
                putBoolean(key, value)
                apply()
            }
        }
        fun saveID(sharedPref : SharedPreferences ,key:String, value:Int) {
            with (sharedPref.edit()) {
                putInt(key, value)
                apply()
            }
        }
        fun containsID(sharedPref : SharedPreferences ,id: String):Boolean {
            return sharedPref.contains(id)
        }
        fun getID(sharedPref : SharedPreferences ,id: String): String? {
            return sharedPref.getString(id,"DefaultNoData")
        }
    }
}