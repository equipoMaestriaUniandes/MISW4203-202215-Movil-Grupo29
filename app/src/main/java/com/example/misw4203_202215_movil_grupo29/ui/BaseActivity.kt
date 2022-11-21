package com.example.misw4203_202215_movil_grupo29.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.FragmentTransaction
import com.example.misw4203_202215_movil_grupo29.R

class BaseActivity : AppCompatActivity() {
    private var startX = 0f
    private var isScrollingRight = false
    private var isScrollingLeft = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action: Int = MotionEventCompat.getActionMasked(event)
        return when (action) {
            MotionEvent.ACTION_MOVE -> {
                isScrollingRight = event.x > startX
                isScrollingLeft = event.x < startX
                Log.d("GESTOS", "Action was MOVE")
                if(isScrollingLeft){
                    Log.d("GESTOS", "LEFT")
                }else if(isScrollingRight){
                    Log.d("GESTOS", "RIGHT")
                }
                true
            }
            MotionEvent.ACTION_UP -> {
                startX = 0f
                Log.d("GESTOS", "Action was UP")
                true
            }
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                Log.d("GESTOS", "Action was DOWN con posicion: $startX")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}