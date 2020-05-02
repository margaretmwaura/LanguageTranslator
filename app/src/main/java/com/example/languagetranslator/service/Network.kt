package com.example.languagetranslator.service

import com.example.languagetranslator.model.Vowels
import kotlinx.coroutines.Deferred
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class Network @Inject constructor(){

    fun getVoewlData() : Deferred<List<Vowels>>
    {
        var listResult : Deferred<List<Vowels>>? = null
        listResult = RetrofitFactory.makeRetrofitService().getVowelData()
        return  listResult
    }

    fun getAudios(filename : String) : Deferred<ResponseBody>
    {
        var audio = RetrofitFactory.makeRetrofitService().getAudios(filename)

        return audio;
    }
}

