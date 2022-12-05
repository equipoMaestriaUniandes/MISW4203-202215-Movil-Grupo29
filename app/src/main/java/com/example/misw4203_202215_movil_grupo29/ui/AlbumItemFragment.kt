package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.AlbumItemFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.viewmodels.TrackViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class AlbumItemFragment : Fragment() {
    private var albumObj: Album? = null
    private var _binding: AlbumItemFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TrackViewModel
    private lateinit var ddTracks: Spinner

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
        ddTracks = _binding!!.trackDropdown
        _binding!!.addTrackBtn.setOnClickListener{
            binding.root.findNavController().navigate(AlbumItemFragmentDirections.actionAlbumItemFragmentToTrackFragment(albumObj!!))
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, TrackViewModel.Factory(activity.application)).get(TrackViewModel::class.java)

        viewModel.refreshDataFromNetwork(albumObj!!.albumId) {
            val trackList = arrayListOf<String>()
            it.forEach {
                trackList.add(it.name + " / " + it.duration)
            }
            val adapter = ArrayAdapter(activity.applicationContext,R.layout.track_adapter,trackList)
            ddTracks.adapter=adapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(albumObj?.cover?.toUri()?.buildUpon()?.scheme("https")?.build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))
            .into(binding.cover)
        _binding!!.albumTitle.text = albumObj?.name
        _binding!!.albumReleaseDate.text = OffsetDateTime.parse(albumObj?.releaseDate).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).toString()
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