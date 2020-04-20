package com.example.languagetranslator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.languagetranslator.R
import com.example.languagetranslator.databinding.ActivityNewWordBinding

class NewWord : AppCompatActivity() {
    private lateinit var wordInEnglish : String
    private lateinit var inYourLanguage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityNewWordBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_new_word
        )
        binding.saveButton.setOnClickListener {
            wordInEnglish = binding.outlinedTextFieldEnglish.editText?.text.toString()
            inYourLanguage = binding.outlinedTextFieldYourLanguage.editText?.text.toString()

        }

    }
}