package com.example.taskone
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import com.example.tableTennis.Location
import com.example.tableTennis.TableTennisClub
import com.google.gson.Gson
import com.yariksoffice.lingver.Lingver
import org.apache.commons.io.FileUtils
import timber.log.Timber
import java.io.File
import java.io.IOException

const val SERIALIZATION_FILE = "serializationObjects.json"
const val MY_SP = "sharedPreferences.data"
class MyApplication: Application() {

    lateinit var tableTennisClub : TableTennisClub
    private lateinit var sharedPref: SharedPreferences
    private lateinit var gson: Gson
    private lateinit var file: File

    private var isDebuggable = false
    private var countAppOpen = 0
    override fun onCreate() {

        super.onCreate()
        Lingver.init(this)
        isDebuggable = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

        if(isDebuggable)
            Timber.plant(Timber.DebugTree())

        gson = Gson()
        file = File(filesDir, SERIALIZATION_FILE)
        Timber.d("File name path ${file.path}")
        initShared()
        initData()

        StatisticUtils.incrementCount(sharedPref,"AppOpenCount")
    }

    fun saveToFile(){ //Klice se v Main activity
        try{
            FileUtils.writeStringToFile(file, gson.toJson(tableTennisClub))
            Timber.d("Saved to file.")
        }catch (e: IOException){
            Timber.d("Can't save " + file.path)
        }
    }

    private fun initShared() {
        sharedPref = getSharedPreferences( MY_SP, Context.MODE_PRIVATE)
    }

    private fun initData(){
        tableTennisClub = try {
            Timber.d("Data from file: ${FileUtils.readFileToString(file)}")
            gson.fromJson(FileUtils.readFileToString(file), TableTennisClub::class.java)
        }catch (e: IOException){
            Timber.d("No file to initialize data.")
            TableTennisClub(Location("Koseskega ulica 10", "Slovenia"),100) // default
        }
    }

    fun activityPaused() {
        StatisticUtils.incrementCount(sharedPref,"AppBackgroundCount")
    }

}

