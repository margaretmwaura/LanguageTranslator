package com.example.languagetranslator.presenter

import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.languagetranslator.model.AppDatabase
import com.example.languagetranslator.model.Vowels
import com.example.languagetranslator.service.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.ResponseBody
import java.io.*
import javax.inject.Inject


class VowelVIewModel @Inject constructor(application: Application, vowelRepository: VowelRepository): AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var vowels = MutableLiveData<List<Vowels>>()

    private val _vowels: LiveData<List<Vowels>>
        get() = vowels


    init {
        vowels.postValue(vowelRepository.allVowels)
    }
}