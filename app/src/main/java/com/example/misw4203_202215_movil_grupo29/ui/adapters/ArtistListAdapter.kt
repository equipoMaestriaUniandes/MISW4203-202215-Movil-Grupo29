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
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.ui.ArtistListFragmentDirections

class ArtistListAdapter(): RecyclerView.Adapter<ArtistListAdapter.ViewHolder>() {

    var bands :List<Band> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ArtistListItemBinding.inflate( LayoutInflater
            .from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bands[position])

        holder.binding.root.setOnClickListener {
            holder.binding.root.findNavController().navigate(ArtistListFragmentDirections.actionArtistListFragmentToArtistItemFragment(bands[position],null))
        }
    }

    override fun getItemCount(): Int {
        return bands.size
    }

    class ViewHolder(val binding: ArtistListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(band: Band){
            binding.artistTitle.text = band.name
            Glide
                .with(binding.root.context)
                .load(band.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(binding.image)
        }

    }
}