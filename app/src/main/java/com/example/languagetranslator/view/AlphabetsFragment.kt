package com.example.languagetranslator.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.databinding.FragmentAlphabetsBinding
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.VowelVIewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AlphabetsFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    private lateinit var vowelViewModel : VowelVIewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAlphabetsBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this

        vowelViewModel = ViewModelProviders.of(this, mViewModelFactory).get(VowelVIewModel::class.java)
//
//        val mp = MediaPlayer()
//        try {
//
//            mp.setDataSource(Environment.getExternalStorageDirectory().path.toString() + "/audio.mp3")
//
//            mp.prepare()
//
//        } catch (e: Exception) {
//
//            e.printStackTrace()
//        }
//
//        binding.audio.setOnClickListener {
//            mp.start()
//        }
        return binding.root
    }

    companion object {

    }
}