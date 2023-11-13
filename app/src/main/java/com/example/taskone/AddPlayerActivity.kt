package com.example.taskone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tableTennis.Location
import com.example.tableTennis.Player
import com.example.tableTennis.TableTennisClub
import com.example.taskone.databinding.ActivityAddPlayerBinding


class AddPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.memPriceInput.addTextChangedListener(MoneyTextWatcher(binding.memPriceInput))
        binding.memPriceInput.setText("0")

        binding.addPlayerButton.setOnClickListener{

            if(isFormValid())
            {
                //val player = Player(binding.memPriceInput.text.toString(),binding.nameInput.text.toString(),binding.surnameInput.text.toString(),binding.rankInput.text.toString().toInt())
                //tableTennisClub.addPlayer(player)

                Toast.makeText(applicationContext,"Player added succesfully", Toast.LENGTH_LONG).show()

                onExit(it)
                clearInputFields()
                binding.memPriceInput.setText("0")
            }
        }

        binding.exitButton.setOnClickListener{finish() }

    }

    private fun onExit(view: android.view.View){
        val data = Intent()
        data.putExtra("membershipPrice", binding.memPriceInput.text.toString())
        data.putExtra("name", binding.nameInput.text.toString())
        data.putExtra("surname", binding.surnameInput.text.toString())
        data.putExtra("localRank", binding.rankInput.text.toString().toInt())
        //data.putExtra("globalRank", binding.nameInput.text)
        setResult(RESULT_OK, data)


        finish()
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
