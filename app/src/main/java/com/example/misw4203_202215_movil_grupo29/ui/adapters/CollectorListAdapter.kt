package com.example.misw4203_202215_movil_grupo29.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.CollectorListItemBinding
import com.example.misw4203_202215_movil_grupo29.models.Collector

class CollectorListAdapter (): RecyclerView.Adapter<CollectorListAdapter.ViewHolder>() {

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Crea una nueva vista
        val binding = CollectorListItemBinding.inflate( LayoutInflater
            .from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Actualiza la vista
        holder.bind(collectors[position])
    }

    override fun getItemCount(): Int {
        //DEvuelve el numero de elementos del adapter
        return collectors.size
    }

    class ViewHolder(private val binding: CollectorListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(collector: Collector){
            binding.nombre.text = collector.name
            binding.telefono.text = "Telefono: ${collector.telephone}"
            binding.email.text = "Email: ${collector.email}"
        }

    }
}