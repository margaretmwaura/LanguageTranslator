package com.example.languagetranslator.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.databinding.FragmentAlphabetsBinding
import com.example.languagetranslator.model.VowelInstanceAdapter
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAlphabetsBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this

        val adapter = VowelInstanceAdapter(VowelInstanceAdapter.VowelInstanceListener{

            val mp = MediaPlayer()
            try {
                mp.setDataSource(Environment.getExternalStorageDirectory().path.toString() + "/" + it.filename)
                mp.prepare()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            mp.setOnCompletionListener { performOnEnd() }
            mp.start()
        })
        vowelViewModel = ViewModelProviders.of(this, mViewModelFactory).get(VowelVIewModel::class.java)

        vowelViewModel.allVowels.observe(activity!!, Observer {
            Log.e("Alphabets","These are the alphabets we have gotten ${it}")
            adapter.submitList(it)
        })

        binding.alphabetsRecyclerViews.adapter= adapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun instantiate(args: Bundle?) {
            val frag = AlphabetsFragment()
            frag.arguments = args
        }
    }
    private fun performOnEnd()
    {
        TODO() // Show an alert dialog then ask the user to try pronouncing the words ,
        // should turn on permission for mic
        Log.e("FINISH","We have played the alphabet")
    }

}



