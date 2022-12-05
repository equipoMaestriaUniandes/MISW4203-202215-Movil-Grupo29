package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ActivityBaseBinding


class BaseActivity : AppCompatActivity() {

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

        binding.btnBack.setOnClickListener {
            activityBack()
        }

    }

    fun inActiveBtn(){
        findViewById<View>(R.id.btn_albumes).isClickable = false
        findViewById<View>(R.id.btn_artistas).isClickable = false
        findViewById<View>(R.id.btn_coleccionistas).isClickable = false
    }

    fun activeBtn(){
        findViewById<View>(R.id.btn_albumes).isClickable = true
        findViewById<View>(R.id.btn_artistas).isClickable = true
        findViewById<View>(R.id.btn_coleccionistas).isClickable = true
    }

    fun activityBack(){
        this.onBackPressed();
    }
}