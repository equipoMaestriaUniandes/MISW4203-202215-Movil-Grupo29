package com.example.misw4203_202215_movil_grupo29.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ArtistListItemBinding
import com.example.misw4203_202215_movil_grupo29.models.Musicians
import com.example.misw4203_202215_movil_grupo29.ui.ArtistListFragmentDirections

class MusicianListAdapter(): RecyclerView.Adapter<MusicianListAdapter.ViewHolder>() {

    var musicians :List<Musicians> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Crea una nueva vista
        val binding = ArtistListItemBinding.inflate( LayoutInflater
            .from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musicians[position])
        holder.binding.root.setOnClickListener {
            holder.binding.root.findNavController().navigate(ArtistListFragmentDirections.actionArtistListFragmentToArtistItemFragment(null,musicians[position]))
        }
    }

    override fun getItemCount(): Int {
        return musicians.size
    }

    class ViewHolder(val binding: ArtistListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(musician: Musicians){
            binding.artistTitle.text = musician.name
            Glide
                .with(binding.root.context)
                .load(musician.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(binding.image)
        }

    }
}