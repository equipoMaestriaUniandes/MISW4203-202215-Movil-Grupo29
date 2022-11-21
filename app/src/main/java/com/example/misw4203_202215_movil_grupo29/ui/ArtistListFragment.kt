package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ArtistListFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.ui.adapters.ArtistListAdapter
import com.example.misw4203_202215_movil_grupo29.viewmodels.BandViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class ArtistListFragment : Fragment() {
    private var _binding: ArtistListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BandViewModel
    private var viewModelAdapter: ArtistListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ArtistListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistListAdapter()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, BandViewModel.Factory(activity.application)).get(BandViewModel::class.java)
        viewModel.bands.observe(viewLifecycleOwner, Observer<List<Band>> {
            it.apply {
                viewModelAdapter!!.bands = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.artistRecycler.adapter = viewModelAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}