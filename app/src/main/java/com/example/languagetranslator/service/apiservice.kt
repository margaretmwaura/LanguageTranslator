package com.example.languagetranslator.service

import com.example.languagetranslator.model.Vowels
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface apiservice {

    @GET("api/v1/{event_types}")
    fun getVowelData()  : Deferred<List<Vowels>>
}