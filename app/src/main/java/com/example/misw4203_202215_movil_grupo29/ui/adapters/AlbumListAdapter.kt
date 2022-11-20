package com.example.misw4203_202215_movil_grupo29.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ViewAlbumCoverBinding
import com.example.misw4203_202215_movil_grupo29.models.Album

class AlbumListAdapter(): RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Crea una nueva vista
        val binding = ViewAlbumCoverBinding.inflate( LayoutInflater
            .from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Actualiza la vista

        holder.bind(albums[position])
    }

    override fun getItemCount(): Int {
        //DEvuelve el numero de elementos del adapter
        return albums.size
    }

    class ViewHolder(private val binding: ViewAlbumCoverBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(album:Album){
            binding.albumTitle.text = album.name
            Glide
                .with(binding.root.context)
                .load(album.cover)
                .into(binding.albumCover)
        }

    }
}