package com.example.taskone

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskone.databinding.ActivityInfoBinding


class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var app: MyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initShared()

        StatisticUtils.incrementCount(sharedPref,"InfoActivityOpenCount")

        binding.infoAppId.text = getString(R.string.app_id_plchldr, SharedPreferencesUtils.getID(sharedPref, "AppID"))

        binding.exitInfoButton.setOnClickListener{finish()}
    }

    private fun initShared() {
        sharedPref = getSharedPreferences( MY_SP, Context.MODE_PRIVATE)
    }


}