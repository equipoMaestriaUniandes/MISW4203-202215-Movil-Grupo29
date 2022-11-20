package com.example.misw4203_202215_movil_grupo29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}