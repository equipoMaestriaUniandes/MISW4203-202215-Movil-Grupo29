package com.example.misw4203_202215_movil_grupo29.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag  = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.initButton.setOnClickListener{
            Toast.makeText(this,"Welcome!", Toast.LENGTH_SHORT).show()
            val intent  = Intent(this, BaseActivity::class.java)
            startActivity((intent))
        }

    }

}