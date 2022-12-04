package com.example.misw4203_202215_movil_grupo29.ui

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
import com.example.misw4203_202215_movil_grupo29.databinding.CollectorItemFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.models.Collector
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class CollectorItemFragment : Fragment() {
    private var collectorObj: Collector? = null
    private var _binding: CollectorItemFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            collectorObj = it.get(COLLECTOR_OBJ_BUNDLE) as Collector?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CollectorItemFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.collectorName.text = collectorObj?.name
        _binding!!.collectorTelephone.text = collectorObj?.telephone
        _binding!!.collectorEmail.text = collectorObj?.email
        if (activity !=null && activity is BaseActivity) {
            (activity as BaseActivity).inActiveBtn()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (activity !=null && activity is BaseActivity) {
            (activity as BaseActivity).activeBtn()
        }
    }

    companion object {
        private const val COLLECTOR_OBJ_BUNDLE = "collectorObj"
        @JvmStatic
        fun newInstance(collectorObj: Collector) =
            CollectorItemFragment().apply {
                arguments = Bundle().apply {
                    putString(COLLECTOR_OBJ_BUNDLE, collectorObj.toString())
                }
            }
    }
}