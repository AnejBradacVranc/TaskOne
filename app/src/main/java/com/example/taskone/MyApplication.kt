package com.example.taskone
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.tableTennis.Location
import com.example.tableTennis.TableTennisClub
import com.google.gson.Gson
import com.yariksoffice.lingver.Lingver
import org.apache.commons.io.FileUtils
import timber.log.Timber
import java.io.File
import java.io.IOException
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.tableTennis.Player
import com.example.tableTennis.PlayersGenerator
import java.util.UUID


const val SERIALIZATION_FILE = "serializationObjects.json"
const val MY_SP = "sharedPreferences.data"
class MyApplication: Application(){

    lateinit var tableTennisClub : TableTennisClub
    private lateinit var sharedPref: SharedPreferences
    private lateinit var gson: Gson
    private lateinit var file: File

    private var isDebuggable = false
    override fun onCreate() {

        super.onCreate()


        isDebuggable = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

        if(isDebuggable)
            Timber.plant(Timber.DebugTree())

        gson = Gson()
        file = File(filesDir, SERIALIZATION_FILE)
        Lingver.init(this)

        ProcessLifecycleOwner.get().lifecycle.addObserver(object: DefaultLifecycleObserver{
            override fun onPause(owner: LifecycleOwner) {
                StatisticUtils.incrementCount(sharedPref,"AppBackgroundCount")
                super.onPause(owner)
            }
        })

        initShared()
        setAppID()
        initData()

        StatisticUtils.incrementCount(sharedPref,"AppOpenCount")

        //tableTennisClub.players = PlayersGenerator.generate(100).toMutableList()
        //saveToFile()
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

    private fun setAppID(){
        if (!SharedPreferencesUtils.containsID(sharedPref, "AppID"))
            SharedPreferencesUtils.saveID(sharedPref, "AppID", UUID.randomUUID().toString().replace("-", ""))

        Timber.d("ID of app is ${SharedPreferencesUtils.getID(sharedPref, "AppID")}")
    }
}

