package com.example.languagetranslator.service

import android.util.Log
import com.example.languagetranslator.model.Vowels
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import okhttp3.Call
import retrofit2.Callback
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class Network @Inject constructor(){

    fun getVowellData() : Observable<List<Vowels>>
    {
        var result = RetrofitFactory.makeRetrofitService().getVowelData()
        return result
    }

    fun getAudios(filename : String) : Observable<ResponseBody>
    {
        var result = RetrofitFactory.makeRetrofitService().getAudios(filename)
        return result;
    }
}

