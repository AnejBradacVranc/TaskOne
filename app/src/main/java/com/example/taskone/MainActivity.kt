package com.example.taskone
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tableTennis.Player
import com.example.taskone.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlayerAdapter
    private lateinit var sharedPref: SharedPreferences
    private lateinit var app : MyApplication
    private var selectedPlayerIndex = -1

    private var getAddedPlayerContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            val data: Intent? = result.data
            val memPrice: String? = data?.getStringExtra("membershipPrice")
            val name : String? = data?.getStringExtra("name")
            val surname : String? = data?.getStringExtra("surname")
            val localRank: Int? = data?.getIntExtra("localRank", -1)
            val isInEditMode: Boolean? = data?.getBooleanExtra("editMode", false)

           if(memPrice != null && name != null && surname != null && localRank != null && isInEditMode !=null)
           {
               val player = Player(memPrice,name,surname,localRank)

               if(!isInEditMode) {
                   app.tableTennisClub.addPlayer(player)
                   adapter.notifyItemChanged(app.tableTennisClub.players.size)
               }
               else if(isInEditMode && selectedPlayerIndex !=-1){
                   app.tableTennisClub.editPlayer(player,selectedPlayerIndex)
                   adapter.notifyItemChanged(selectedPlayerIndex)
               }
               //adapter.notifyDataSetChanged()
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

        binding.addPlayerBtn.setOnClickListener {onOpenAddPlayerActivity(it) }
        binding.infoButtonMain.setOnClickListener{onOpenInfoActivity(it) }
        binding.exitButtonMain.setOnClickListener { finish() }
        binding.settingsButton.setOnClickListener {  onOpenSettingsActivity(it)}

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlayerAdapter(app.tableTennisClub,object:PlayerAdapter.MyOnClick {

            override fun onClick(p0: View?, position: Int) {
                Timber.d("Position $position")
                val data = intent
                data.putExtra("SELECTED_ID", app.tableTennisClub.players[position].id)

                if(p0!=null)
                {
                    val intent = Intent(p0.context, AddPlayerActivity::class.java)

                    selectedPlayerIndex = position

                    intent.putExtra("selectedIndex",position)
                    intent.putExtra("editMode", true)

                    getAddedPlayerContent.launch(intent)
                }
                setResult(RESULT_OK, data)
            }

            override fun onLongClick(p0: View?, position: Int) : Boolean {
                val builder = AlertDialog.Builder(this@MainActivity)

                builder.setTitle("Delete")
                builder.setMessage("Player ${app.tableTennisClub.players[position]}")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes"){ _, _ ->
                    app.tableTennisClub.removePlayer(position) //Se warning in confirmation
                    adapter.notifyItemRemoved(position)
                    app.saveToFile()
                }
                builder.setNeutralButton("Cancel"){ _, _ -> //performing cancel action

                }
                builder.setNegativeButton("No"){ _, _ -> //performing negative action
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

                return true
            }
        })
        binding.recyclerView.adapter = adapter

        StatisticUtils.incrementCount(sharedPref,"MainActivityOpenCount")
    }

    private fun onOpenAddPlayerActivity(view : android.view.View){
        val intent = Intent(this, AddPlayerActivity::class.java)
        intent.putExtra("editMode", false)
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


}