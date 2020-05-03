package com.example.languagetranslator.service

import com.example.languagetranslator.model.Vowels
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface apiservice {

    @Headers("Accept: application/json")
    @GET("api/french_words")
    fun getVowelData()  : Observable<List<Vowels>>

    @Headers("Accept: application/json")
    @GET("api/videos")
    fun getAudios(@Query("filename") name : String) : Observable<ResponseBody>
}