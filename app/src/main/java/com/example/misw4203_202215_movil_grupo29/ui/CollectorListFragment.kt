package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.CollectorListFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Collector
import com.example.misw4203_202215_movil_grupo29.ui.adapters.CollectorListAdapter
import com.example.misw4203_202215_movil_grupo29.viewmodels.CollectorViewModel


class CollectorListFragment : Fragment() {
    private var _binding: CollectorListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel
    private var viewModelAdapter: CollectorListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CollectorListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorListAdapter()

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(activity.application)).get(CollectorViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Collector>> {
            it.apply {
                viewModelAdapter!!.collectors = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.collectorRecycler.adapter = viewModelAdapter
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