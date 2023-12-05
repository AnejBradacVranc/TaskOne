package com.example.taskone

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.taskone.databinding.ActivitySettingsBinding
import timber.log.Timber
import java.util.UUID
import com.yariksoffice.lingver.Lingver

class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var app : MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settingsDropdownSpinner: Spinner = binding.settingsLanguagesDropdown
        val enableQRSwitcher: SwitchCompat = binding.enableQrSwitch

        initShared()

        StatisticUtils.incrementCount(sharedPref,"SettingsActivityOpenCount")

        if (!SharedPreferencesUtils.containsID(sharedPref, "AppID"))
            SharedPreferencesUtils.saveID(sharedPref, "AppID", UUID.randomUUID().toString().replace("-", ""))

        Timber.d("ID of app is ${SharedPreferencesUtils.getID(sharedPref, "AppID")}")

        initSpinner(settingsDropdownSpinner)

        enableQRSwitcher.isChecked = sharedPref.getBoolean("QR_Enabled", true)
        settingsDropdownSpinner.setSelection(sharedPref.getInt("CurrentLanguageIndex", 0))

        enableQRSwitcher.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferencesUtils.saveID(sharedPref, "QR_Enabled", isChecked)
        }
    }

    private fun initSpinner(spinner: Spinner){

        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.languages_array,
            R.layout.my_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }
    }
    private fun initShared() {
        sharedPref = getSharedPreferences(MY_SP, Context.MODE_PRIVATE)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val currentLanguageIndex = sharedPref.getInt("CurrentLanguageIndex", 0)

        if (pos != currentLanguageIndex) {
            val languageCodes = resources.getStringArray(R.array.languages_array)
            SharedPreferencesUtils.saveID(sharedPref, "CurrentLanguageIndex", pos)

            Lingver.getInstance().setLocale(this, languageCodes[pos].takeLast(2))
            recreate()
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onPause() {
        super.onPause()
        app.activityPaused()
    }
}