package com.example.taskone

import android.os.Bundle
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tableTennis.Location
import com.example.tableTennis.Player
import com.example.tableTennis.TableTennisClub
import com.example.taskone.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tableTennisClub = TableTennisClub(Location("Koseskega ulica 10", "Slovenia"),100)

        binding.memPriceInput.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->

            if(!hasFocus){
                if(binding.memPriceInput.text.matches("\\d+".toRegex()))
                    binding.memPriceInput.text.append('$')

                if(!binding.memPriceInput.text.matches("\\d+\\$".toRegex()) && !binding.memPriceInput.text.isBlank())
                {
                    binding.memPriceInput.text.clear()
                    binding.memPriceInput.setError("Invalid input")
                }

            }

        })

        binding.addPlayerButton.setOnClickListener{

            if(isFormValid())
            {
                val player = Player(binding.memPriceInput.text.toString(),binding.nameInput.text.toString(),binding.surnameInput.text.toString(),binding.rankInput.text.toString().toInt())
                tableTennisClub.addPlayer(player)
                clearInputFields()
                Toast.makeText(applicationContext,"Player added succesfully", Toast.LENGTH_LONG).show()
            }
        }

        binding.infoButton.setOnClickListener{Log.w("TableTennisClubApp", "Stevilo igralcev v klubu: ${tableTennisClub.size()}")}

    }

    private fun clearInputFields(){

        val inputsList = listOf<EditText>(binding.memPriceInput,binding.nameInput,binding.surnameInput,binding.rankInput)

        inputsList.forEach { it.text.clear() }
    }

    private fun isFormValid(): Boolean{

        var isFormValid = true
        val inputsList = listOf<EditText>( binding.memPriceInput, binding.nameInput,binding.surnameInput,binding.rankInput)

        inputsList.forEach {
            if(it.text.isBlank()){
                it.error = "Value can't be empty!"
                isFormValid = false
            }
        }

        return isFormValid
    }

}
