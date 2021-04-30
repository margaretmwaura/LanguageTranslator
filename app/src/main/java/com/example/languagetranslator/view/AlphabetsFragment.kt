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
import com.example.languagetranslator.R
import com.example.languagetranslator.model.VowelInstanceAdapter
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.VowelVIewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.microphone_alert.*
import javax.inject.Inject
import com.example.languagetranslator.databinding.FragmentAlphabetsBinding


class AlphabetsFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    private lateinit var vowelViewModel : VowelVIewModel

    private lateinit  var alert : androidx.appcompat.app.AlertDialog

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
            mp.setOnCompletionListener {
//                performOnEnd()
            }
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
       createAlertDialogBox()
    }

    private fun createAlertDialogBox()
    {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)
        val factory = LayoutInflater.from(activity!!)
        val view  = factory.inflate(R.layout.microphone_alert, null)
        alert = builder.create()
        alert.setView(view)
        alert.setCancelable(true)
        alert.show()

        alert.okay.setOnClickListener {
            Log.e("OKAY","We have got to turn on the mic")
        }

        alert.cancel.setOnClickListener {
            Log.e("CANCEL","They cancelled the practice")
        }
    }

}



