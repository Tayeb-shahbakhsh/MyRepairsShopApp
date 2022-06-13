package com.example.repaitshopapplication.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.databinding.ActivityMainBinding
import com.example.repaitshopapplication.databinding.DialogCreateProductBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityMainBottomNavigationView.background = null
        setupNavController()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.fab.setOnClickListener(this)
    }

    private fun createProductDialog() {
        val view = DialogCreateProductBinding.inflate(layoutInflater)
        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        view.saveBtn.setOnClickListener(this)
    }

    private fun setupNavController() {
        val navController: NavController = Navigation.findNavController(
            this,
            R.id.navHostFragmentContainer
        )
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab -> createProductDialog()
            R.id.saveBtn -> saveProduct()
        }
    }

    private fun saveProduct() {
        dialog.hide()
    }
}
