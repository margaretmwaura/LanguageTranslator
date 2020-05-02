package com.example.languagetranslator.service

import com.example.languagetranslator.model.Vowels
import kotlinx.coroutines.Deferred
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface apiservice {

    @Headers("Accept: application/json")
    @GET("api/french_words")
    fun getVowelData()  : Deferred<List<Vowels>>

    @Headers("Accept: application/json")
    @GET("api/videos")
    fun getAudios(@Query("filename") name : String) :ResponseBody
}