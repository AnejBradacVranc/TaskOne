package com.example.taskone

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.taskone.databinding.ActivityAddPlayerBinding
import timber.log.Timber
import java.math.BigDecimal

class AddPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayerBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var app : MyApplication

    private var getData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val res: String? = data?.getStringExtra("SCAN_RESULT")

            if(res!=null)
                updateInputFields(res)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initShared()

        StatisticUtils.incrementCount(sharedPref,"AddPlayerActivityOpenCount")

        binding.memPriceInput.addTextChangedListener(MoneyTextWatcher(binding.memPriceInput))
        binding.memPriceInput.setText("0")

        binding.addPlayerButton.setOnClickListener{

            if(isFormValid())
            {
                Toast.makeText(applicationContext,getString(R.string.player_added_succ), Toast.LENGTH_LONG).show()

                onExit(it)
                clearInputFields()
                binding.memPriceInput.setText("0")
            }
        }

        binding.exitButton.setOnClickListener{finish() }

        if(sharedPref.getBoolean("QR_Enabled", true)){
            binding.qrScanButton.setOnClickListener { onScanQrCode(it) }
        }else{
            binding.qrScanButton.isVisible = false
        }


    }

    private fun updateInputFields(res: String){

            val dataArray : List<Any>

            try{
                dataArray = res.split(';')

                if(dataArray.size !=4 || dataArray[3].toIntOrNull() == null || MoneyTextWatcher.parseCurrencyValue(dataArray[2]) == BigDecimal.ZERO || dataArray[0].contains("[0-9]".toRegex()) || dataArray[1].contains("[0-9]".toRegex()))
                {
                    onIncorrectQRCode()
                }
                else{

                    binding.nameInput.setText(dataArray[0])
                    binding.surnameInput.setText(dataArray[1])
                    binding.memPriceInput.setText(dataArray[2])
                    binding.rankInput.setText(dataArray[3])
                }
            }catch (e:Exception){
                onIncorrectQRCode()
            }

    }

    private fun onIncorrectQRCode(){
        clearInputFields()
        Toast.makeText(applicationContext,getString(R.string.incorrect_qr), Toast.LENGTH_LONG).show()
        val vibrator = this.getSystemService(Vibrator::class.java)
        vibrator.vibrate(VibrationEffect.createOneShot(800,50))
    }

    private fun onScanQrCode(view: android.view.View) {
        try {
            val intent = Intent("com.google.zxing.client.android.SCAN")
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // use “PRODUCT_MODE” for barcodes
            getData.launch(intent)
        } catch (e: Exception) {
            val marketUri = Uri.parse("market://details?id=com.google.zxing.client.android")
            val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
            startActivity(marketIntent)
        }
    }


    private fun onExit(view: android.view.View){
        val data = Intent()
        data.putExtra("membershipPrice", binding.memPriceInput.text.toString())
        data.putExtra("name", binding.nameInput.text.toString())
        data.putExtra("surname", binding.surnameInput.text.toString())
        data.putExtra("localRank", binding.rankInput.text.toString().toInt())

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
                it.error = getString(R.string.empty_val_logcat_error)
                isFormValid = false
            }
        }

        return isFormValid
    }

    private fun initShared() {
        sharedPref = getSharedPreferences( MY_SP, Context.MODE_PRIVATE)
    }

    override fun onPause() {
        super.onPause()
        app.activityPaused()
    }
}
