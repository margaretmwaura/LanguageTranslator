package com.example.languagetranslator.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.R
import com.example.languagetranslator.databinding.FragmentAlphabetsBinding
import com.example.languagetranslator.databinding.FragmentListBinding
import com.example.languagetranslator.presenter.NewWordViewModel
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

        return binding.root
    }

    companion object {

    }
}