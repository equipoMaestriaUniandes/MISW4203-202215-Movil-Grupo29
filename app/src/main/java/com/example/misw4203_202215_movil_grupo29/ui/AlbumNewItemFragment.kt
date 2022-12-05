package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.AlbumNewItemFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.viewmodels.AlbumViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.json.JSONObject


class AlbumNewItemFragment : Fragment() {
    private var _binding: AlbumNewItemFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel
    val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumNewItemFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val spinnerGenre : Spinner = binding.albumNewItemGenre
        if (spinnerGenre != null) {
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.genre_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerGenre.adapter = adapter
            }
        }

        val spinnerRecordLabel : Spinner = binding.albumNewItemRecordLabel
        if (spinnerRecordLabel != null) {
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.record_label_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerRecordLabel.adapter = adapter
            }
        }
        binding.btnAlbumNewItem.setOnClickListener{
            GlobalScope.launch(Dispatchers.Main){
                save()
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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

    private suspend fun save(){
        val newAlbumJson= JSONObject(gson.toJson(Album(0,
            binding.albumNewItemName.text.toString(),
            "https://www.actbus.net/fleetwiki/images/8/84/Noimage.jpg",
            binding.albumNewItemReleaseDate.text.toString(),
            binding.albumNewItemDescription.text.toString(),
            binding.albumNewItemGenre.selectedItem.toString(),
            binding.albumNewItemRecordLabel.selectedItem.toString()
        )))
        newAlbumJson.remove("albumId")
        val value = GlobalScope.async { // creates worker thread
            var res = withContext(Dispatchers.Default) {
               viewModel.createDataFromNetwork(newAlbumJson)
            }
        }
        value.await()
        if (activity !=null && activity is BaseActivity) {
            (activity as BaseActivity).activityBack()
        }
    }
}