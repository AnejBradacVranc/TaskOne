package com.example.taskone
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.tableTennis.Location
import com.example.tableTennis.Player
import com.example.tableTennis.TableTennisClub
import com.example.taskone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var app : MyApplication

    private var getAddedPlayerContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            val data: Intent? = result.data
            val memPrice: String? = data?.getStringExtra("membershipPrice")
            val name : String? = data?.getStringExtra("name")
            val surname : String? = data?.getStringExtra("surname")
            val localRank: Int? = data?.getIntExtra("localRank", -1)

           if(memPrice != null && name != null && surname != null && localRank != null)
           {
               app.tableTennisClub.addPlayer(Player(memPrice,name,surname,localRank))
               binding.addedPname.isVisible = true
               binding.addedMemPriceTw.text = getString(R.string.membership_price_plchldr, memPrice)
               binding.addedNmSurTw.text = getString(R.string.name_surname_plchldr,"$name $surname")
               binding.addedLclRankingTw.text = getString(R.string.local_ranking_plchldr, localRank.toString())
           }

            app.saveToFile()

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initShared()

        StatisticUtils.incrementCount(sharedPref,"MainActivityOpenCount")
        binding.addedPname.isVisible = false

        binding.addPlayerBtn.setOnClickListener {onOpenAddPlayerActivity(it) }

        binding.infoButtonMain.setOnClickListener{onOpenInfoActivity(it) }

        binding.exitButtonMain.setOnClickListener { finish() }

        binding.settingsButton.setOnClickListener {  onOpenSettingsActivity(it)}

    }

    private fun onOpenAddPlayerActivity(view : android.view.View){
        val intent = Intent(this, AddPlayerActivity::class.java)
        getAddedPlayerContent.launch(intent)
    }

    private fun onOpenInfoActivity(view: android.view.View){
        startActivity(Intent(this,InfoActivity::class.java))
    }

    private fun onOpenSettingsActivity(view: android.view.View){
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun initShared() {
        sharedPref = getSharedPreferences( MY_SP, Context.MODE_PRIVATE)
    }

    override fun onPause() {
        super.onPause()
        app.activityPaused()
    }


}