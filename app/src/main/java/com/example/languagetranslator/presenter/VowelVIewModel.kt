package com.example.languagetranslator.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.languagetranslator.model.Vowels
import com.example.languagetranslator.service.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class VowelVIewModel @Inject constructor(val network : Network):  ViewModel(){
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var vowels = MutableLiveData<List<Vowels>>()

    private val _vowels : LiveData<List<Vowels>>
         get() = vowels

    init {
        getAllTheVowels()
    }

    private fun getAllTheVowels()
    {
        coroutineScope.launch {

            var results = network.getVoewlData()

            try {
                 val data = results.await()
                Log.d("Data","Here is the data my people $data")
                 vowels.postValue(data)
            }catch (e : Exception)
            {

            }
        }
    }
}