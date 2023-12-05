package com.example.taskone

import android.content.SharedPreferences
import timber.log.Timber

class StatisticUtils {

    companion object{
        fun incrementCount(sharedPref:SharedPreferences, key:String ){

            if(!SharedPreferencesUtils.containsID(sharedPref,key))
                SharedPreferencesUtils.saveID(sharedPref,key,0)

            var countAppOpen = sharedPref.getInt(key,0)

            countAppOpen++

            SharedPreferencesUtils.saveID(sharedPref,key,countAppOpen)

            Timber.d("Key: $key, opened $countAppOpen times")
        }
    }
}