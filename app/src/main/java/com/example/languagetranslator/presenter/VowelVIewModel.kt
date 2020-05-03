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


class VowelVIewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var vowels = MutableLiveData<List<Vowels>>()

    private val _vowels: LiveData<List<Vowels>>
        get() = vowels

    private lateinit var repository : VowelRepository
    init {
//        val vowelDao = AppDatabase.invoke(application).vowelInstanceDao()
//        repository = VowelRepository(vowelDao)
//        getAllTheVowels()
    }

//    private fun getAllTheVowels() {
//        coroutineScope.launch {
//            var results = network.getVoewlData()
//
//            try {
//                val data = results.await()
//
//                Log.d("Data", "Here is the data my people $data")
//
//                vowels.postValue(data)
//
////                repository.insertAll(data)
//
////                data.forEach{
////                    getAudio(it.filename)
////                }
//            } catch (e: Exception) {
//                Log.e("Babes", "Babes there was an error ${e.message}")
//            }
//        }
//    }


}