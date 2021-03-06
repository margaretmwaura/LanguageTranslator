package com.example.languagetranslator.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.languagetranslator.databinding.FragmentTranslateBinding


class TranslateFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTranslateBinding.inflate(inflater,container,false)

        binding.fab.setOnClickListener {
            val intent = Intent(activity!!, NewWord::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {
            @JvmStatic
            fun instantiate(args: Bundle?) {
                val frag = TranslateFragment()
                frag.arguments = args
            }
        }


}