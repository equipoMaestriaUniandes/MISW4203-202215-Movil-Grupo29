package com.example.misw4203_202215_movil_grupo29.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityMainBinding

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
            val intent  = Intent(this, BaseActivity::class.java)
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