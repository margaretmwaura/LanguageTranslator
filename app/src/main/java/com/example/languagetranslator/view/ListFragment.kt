package com.example.languagetranslator.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.databinding.FragmentListBinding
import com.example.languagetranslator.model.WordInstanceAdapter
import com.example.languagetranslator.presenter.NewWordViewModel
import com.example.languagetranslator.presenter.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    private lateinit var addNewWordViewModel : NewWordViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this

        addNewWordViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewWordViewModel::class.java)

        val wordInsanceAdapter = WordInstanceAdapter(WordInstanceAdapter.WordInstanceListener{

        })
        addNewWordViewModel.words.observe(activity!!, Observer {
            wordInsanceAdapter.submitList(it)
        })
        binding.wordsRecyclerViews.adapter = wordInsanceAdapter
        return binding.root
    }

    companion object
    {
        @JvmStatic
        fun instantiate(args: Bundle?) {
            val frag = ListFragment()
            frag.arguments = args
        }
    }


}