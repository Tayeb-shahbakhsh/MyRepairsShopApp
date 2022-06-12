package com.example.repaitshopapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.repaitshopapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityMainBottomNavigationView.background = null

        setupNavController()
    }

    private fun setupNavController() {

        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragmentContainer)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)
    }
}