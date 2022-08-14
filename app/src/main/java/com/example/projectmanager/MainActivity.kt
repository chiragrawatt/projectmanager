package com.example.projectmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.projectmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        val actionBar = supportActionBar

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val findNavController = this.findNavController(R.id.myNavHostFragment)
        return findNavController.navigateUp()
    }
}