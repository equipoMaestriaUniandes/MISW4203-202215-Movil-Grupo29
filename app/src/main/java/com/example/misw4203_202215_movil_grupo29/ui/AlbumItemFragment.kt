package com.example.misw4203_202215_movil_grupo29.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.AlbumItemFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.ui.adapters.AlbumListAdapter
import com.example.misw4203_202215_movil_grupo29.viewmodels.AlbumViewModel
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

//private const val ARG_PARAM1 = "param1"

class AlbumItemFragment : Fragment() {
    private var albumObj: Album? = null
    private var _binding: AlbumItemFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumObj = it.get(ALBUM_OBJ_BUNDLE) as Album?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumItemFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(binding.root.context, AlbumItemFragment::class.java)

       // val albumItem = intent.getParcelableExtra<Album>(ALBUM_OBJ_BUNDLE) as Album
        Glide.with(this)
            .load(albumObj?.cover?.toUri()?.buildUpon()?.scheme("https")?.build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))
            .into(binding.cover)
        var myDate = OffsetDateTime.parse(albumObj?.releaseDate).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))
        _binding!!.albumTitle.text = albumObj?.name
        _binding!!.albumReleaseDate.text = myDate.toString()
        _binding!!.albumGenre.text = albumObj?.genre
        _binding!!.albumRecordLabel.text = albumObj?.recordLabel
        _binding!!.albumDescription.text = albumObj?.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ALBUM_OBJ_BUNDLE = "albumObj"
        @JvmStatic
        fun newInstance(albumObj: Album) =
            AlbumItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ALBUM_OBJ_BUNDLE, albumObj.toString())
                }
            }
    }
}