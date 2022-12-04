package com.example.misw4203_202215_movil_grupo29.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.misw4203_202215_movil_grupo29.R
import com.example.misw4203_202215_movil_grupo29.databinding.ArtistItemFragmentBinding
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.models.Musicians
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base.view.*
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ArtistItemFragment : Fragment() {
    private var bandObj: Band? = null
    private var musicianObj: Musicians? = null
    private var _binding: ArtistItemFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bandObj = it.get(BAND_OBJ_BUNDLE) as Band?
            musicianObj = it.get(MUSICIAN_OBJ_BUNDLE) as Musicians?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArtistItemFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (bandObj!=null) {
            Glide.with(this)
                .load(bandObj?.image?.toUri()?.buildUpon()?.scheme("https")?.build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.artistImage)
            _binding!!.artistName.text = bandObj?.name
            _binding!!.artistDate.text = OffsetDateTime.parse(bandObj?.creationDate)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))?.toString()
            _binding!!.artistDescription.text = bandObj?.description
        }else{
            Glide.with(this)
                .load(musicianObj?.image?.toUri()?.buildUpon()?.scheme("https")?.build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.artistImage)
            _binding!!.artistName.text = musicianObj?.name
            _binding!!.artistDate.text = OffsetDateTime.parse(musicianObj?.birthDate)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))?.toString()
            _binding!!.artistDescription.text = musicianObj?.description
        }
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
        private const val BAND_OBJ_BUNDLE = "bandObj"
        private const val MUSICIAN_OBJ_BUNDLE = "musicianObj"
        @JvmStatic
        fun newInstance(bandObj: Band?, musicianObj:Musicians?) =
            ArtistItemFragment().apply {
                arguments = Bundle().apply {
                    putString(BAND_OBJ_BUNDLE, bandObj.toString())
                    putString(MUSICIAN_OBJ_BUNDLE, musicianObj.toString())
                }
            }
    }
}