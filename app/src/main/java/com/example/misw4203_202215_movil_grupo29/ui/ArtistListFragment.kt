package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.example.misw4203_202215_movil_grupo29.databinding.ArtistListFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.models.Musicians
import com.example.misw4203_202215_movil_grupo29.ui.adapters.ArtistListAdapter
import com.example.misw4203_202215_movil_grupo29.ui.adapters.MusicianListAdapter
import com.example.misw4203_202215_movil_grupo29.viewmodels.BandViewModel
import com.example.misw4203_202215_movil_grupo29.viewmodels.MusicianViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class ArtistListFragment : Fragment() {
    private var _binding: ArtistListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelBand: BandViewModel
    private lateinit var viewModelMusician: MusicianViewModel
    private var viewModelBandAdapter: ArtistListAdapter? = null
    private var viewModelMusicianAdapter: MusicianListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ArtistListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelBandAdapter = ArtistListAdapter()
        viewModelMusicianAdapter = MusicianListAdapter()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModelBand = ViewModelProvider(this, BandViewModel.Factory(activity.application))[BandViewModel::class.java]
        viewModelBand.bands.observe(viewLifecycleOwner, Observer<List<Band>> {
            it.apply {
                viewModelBandAdapter!!.bands = this
            }
        })

        viewModelBand.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        viewModelMusician = ViewModelProvider(this, MusicianViewModel.Factory(activity.application))[MusicianViewModel::class.java]
        viewModelMusician.musicians.observe(viewLifecycleOwner, Observer<List<Musicians>> {
            it.apply {
                viewModelMusicianAdapter!!.musicians = this
            }
        })

        viewModelMusician.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val concatenated = ConcatAdapter(viewModelMusicianAdapter, viewModelBandAdapter)
        binding.artistRecycler.adapter = concatenated
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModelBand.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModelBand.onNetworkErrorShown()
        }

        if(!viewModelMusician.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModelMusician.onNetworkErrorShown()
        }
    }

}