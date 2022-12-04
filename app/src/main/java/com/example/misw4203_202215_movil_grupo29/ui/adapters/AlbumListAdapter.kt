package com.example.misw4203_202215_movil_grupo29.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.AlbumListItemBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.ui.AlbumListFragmentDirections

class AlbumListAdapter(): RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Crea una nueva vista
        val binding = AlbumListItemBinding.inflate( LayoutInflater
            .from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Actualiza la vista
        holder.binding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position])

        holder.binding.root.setOnClickListener {
            val action = AlbumListFragmentDirections.actionAlbumListFragmentToAlbumItemFragment(albums[position])
            // Navigate using that action
            holder.binding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        //DEvuelve el numero de elementos del adapter
        return albums.size
    }

    class ViewHolder(val binding: AlbumListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(album:Album){
            binding.albumTitle.text = album.name
            Glide
                .with(binding.root.context)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(binding.cover)
        }

    }
}