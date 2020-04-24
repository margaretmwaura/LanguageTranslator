package com.example.languagetranslator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.languagetranslator.R
import com.example.languagetranslator.databinding.ActivityNewWordBinding
import com.example.languagetranslator.model.WordInstance
import com.example.languagetranslator.presenter.NewWordViewModel
import com.example.languagetranslator.presenter.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewWord : AppCompatActivity() , HasSupportFragmentInjector {
    private lateinit var wordInEnglish : String
    private lateinit var inYourLanguage: String
    private lateinit var addNewWordViewModel : NewWordViewModel

    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val disposable = CompositeDisposable()

    override fun supportFragmentInjector() = dispatchingAndroidInjector
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding : ActivityNewWordBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_new_word
        )

        addNewWordViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewWordViewModel::class.java)

        binding.saveButton.setOnClickListener {
            wordInEnglish = binding.outlinedTextFieldEnglish.editText?.text.toString()
            inYourLanguage = binding.outlinedTextFieldYourLanguage.editText?.text.toString()

            val wordInstance = WordInstance(0,wordInEnglish,inYourLanguage)

            disposable.add(addNewWordViewModel.saveNewWord(wordInstance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Toast.makeText(this, "The name was successfully added", Toast.LENGTH_SHORT).show()},
                    { error -> Log.e("Adding name", "Unable to update username, ${error}) " )})) }



    }



}