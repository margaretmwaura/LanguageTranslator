package com.example.languagetranslator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.R
import com.example.languagetranslator.databinding.ActivityNewWordBinding
import com.example.languagetranslator.presenter.NewWordViewModel
import com.example.languagetranslator.presenter.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class NewWord : AppCompatActivity() , HasSupportFragmentInjector {
    private lateinit var wordInEnglish : String
    private lateinit var inYourLanguage: String
    private lateinit var addNewWordViewModel : NewWordViewModel

    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding : ActivityNewWordBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_new_word
        )
        binding.saveButton.setOnClickListener {
            wordInEnglish = binding.outlinedTextFieldEnglish.editText?.text.toString()
            inYourLanguage = binding.outlinedTextFieldYourLanguage.editText?.text.toString()

        }

        addNewWordViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewWordViewModel::class.java)

    }

}