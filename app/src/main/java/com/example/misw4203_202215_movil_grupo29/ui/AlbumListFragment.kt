package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.AlbumListFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.ui.adapters.AlbumListAdapter
import com.example.misw4203_202215_movil_grupo29.viewmodels.AlbumViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumListFragment : Fragment() {
    private var _binding: AlbumListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnAlbumNewShow.setOnClickListener{
            binding.root.findNavController().navigate(AlbumListFragmentDirections.actionAlbumListFragmentToAlbumNewItemFragment())
        }

        viewModelAdapter = AlbumListAdapter()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                viewModelAdapter!!.albums = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.albumRecycler.adapter = viewModelAdapter
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