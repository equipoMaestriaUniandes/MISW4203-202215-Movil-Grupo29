package com.example.misw4203_202215_movil_grupo29.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misw4203_202215_movil_grupo29.BaseActivity
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityMainBinding
import com.example.misw4203_202215_movil_grupo29.utilities.BitmapScaler
import com.example.misw4203_202215_movil_grupo29.utilities.DeviceDimensionsHelper

class MainActivity : AppCompatActivity() {
    private val tag  = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(tag,"OnCreate")
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.initButton.setOnClickListener{
            Log.d(tag,"Start Button")
            Toast.makeText(this,"Welcome!", Toast.LENGTH_SHORT).show()
            val intent  = Intent(this,BaseActivity::class.java)
            startActivity((intent))
        }



/*
        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)*/

    }

}