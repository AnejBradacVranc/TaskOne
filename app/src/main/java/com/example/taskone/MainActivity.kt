package com.example.taskone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.tableTennis.Location
import com.example.tableTennis.Player
import com.example.tableTennis.TableTennisClub
import com.example.taskone.databinding.ActivityAddPlayerBinding
import com.example.taskone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var tableTennisClub = TableTennisClub(Location("Koseskega ulica 10", "Slovenia"),100)
    private var getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            val data: Intent? = result.data
            val memPrice: String? = data?.getStringExtra("membershipPrice")
            val name : String? = data?.getStringExtra("name")
            val surname : String? = data?.getStringExtra("surname")
            val localRank: Int? = data?.getIntExtra("localRank", -1)

           if(memPrice != null && name != null && surname != null && localRank != null)
           {
               tableTennisClub.addPlayer(Player(memPrice,name,surname,localRank))
               binding.addedPname.isVisible = true
               binding.addedMemPriceTw.text = "Membership price: ${memPrice}";
               binding.addedNmSurTw.text = "Name and surname: ${name} ${surname}";
               binding.addedLclRankingTw.text = "Local ranking: ${localRank.toString()}";
           }

            Log.i("test",tableTennisClub.toString())

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addedPname.isVisible = false


        binding.addPlayerBtn.setOnClickListener {

            val intent = Intent(this, AddPlayerActivity::class.java)
            getContent.launch(intent)
        }
    }


}