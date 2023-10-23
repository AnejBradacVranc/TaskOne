package com.example.taskone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskone.databinding.ActivityMainBinding
import com.example.tableTennis.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.helloWorldView.setText("Test")


    }
}