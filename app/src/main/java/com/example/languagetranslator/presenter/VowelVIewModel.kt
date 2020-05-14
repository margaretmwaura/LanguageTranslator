package com.example.languagetranslator.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.languagetranslator.model.Vowels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject


class VowelVIewModel @Inject constructor(application: Application, vowelRepository: VowelRepository): AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var vowels = MutableLiveData<List<Vowels>>()

    val allVowels: LiveData<List<Vowels>>
        get() = vowels


    init {
        vowels.postValue(vowelRepository.allVowels)
    }
}