package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.TrackFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.models.Track
import com.example.misw4203_202215_movil_grupo29.viewmodels.TrackViewModel
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class TrackFragment : Fragment() {
    private var albumObj: Album? = null
    private var _binding: TrackFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TrackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumObj = it.get(TrackFragment.ALBUM_OBJ_BUNDLE) as Album?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TrackFragmentBinding.inflate(inflater, container, false)

        _binding!!.btnCancelar.setOnClickListener{
            val action = TrackFragmentDirections.actionTrackFragmentToAlbumItemFragment(albumObj!!)
            binding.root.findNavController().navigate(action)
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

        _binding!!.btnCrear.setOnClickListener{
            var strTrack="{\n    \"name\": \""+binding.trackName.text.toString()+"\",\n    \"duration\": \""+binding.duration.text.toString()+"\"\n}"
            viewModel!!.createTrackFromNetwork(JSONObject(strTrack), albumObj!!.albumId)
            val action = TrackFragmentDirections.actionTrackFragmentToAlbumItemFragment(albumObj!!)
            binding.root.findNavController().navigate(action)
            Toast.makeText(this.context,"Track created!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ALBUM_OBJ_BUNDLE = "albumObj"

        @JvmStatic
        fun newInstance(trackObj: Track) =
            TrackFragment().apply {
                arguments = Bundle().apply {
                    putString(ALBUM_OBJ_BUNDLE, trackObj.toString())
                }
            }
    }
}