package com.example.misw4203_202215_movil_grupo29.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityBaseBinding
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityMainBinding

class BaseActivity : AppCompatActivity() {
    //private var startX = 0f
    //private var isScrollingRight = false
    //private var isScrollingLeft = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArtistas.setOnClickListener {
            if (binding.pageTitle.text == "Collector"){
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_collectorListFragment_to_artistListFragment)
            }else if  (binding.pageTitle.text == "Album"){
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_albumListFragment_to_artistListFragment)
            }
            binding.btnArtistas.isClickable = false
            binding.btnColeccionistas.isClickable = true
            binding.btnAlbumes.isClickable = true
            binding.pageTitle.text = "Artist"
        }

        binding.btnAlbumes.setOnClickListener {

            if (binding.pageTitle.text == "Collector") {
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_collectorListFragment_to_albumListFragment)
            }else  if (binding.pageTitle.text == "Artist") {
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_artistListFragment_to_albumListFragment)
            }

            binding.btnAlbumes.isClickable = false
            binding.btnArtistas.isClickable = true
            binding.btnColeccionistas.isClickable = true
            binding.pageTitle.text = "Album"

        }

        binding.btnColeccionistas.setOnClickListener {

            if (binding.pageTitle.text == "Album") {
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_albumListFragment_to_collectorListFragment)
            }else  if (binding.pageTitle.text == "Artist") {
                this.findNavController(R.id.fragmentContainerView).navigate(R.id.action_artistListFragment_to_collectorListFragment)
            }

            binding.btnColeccionistas.isClickable = false
            binding.btnArtistas.isClickable = true
            binding.btnAlbumes.isClickable = true
            binding.pageTitle.text = "Collector"
        }

    }
    /*override fun onTouchEvent(event: MotionEvent): Boolean {
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
    }*/
}