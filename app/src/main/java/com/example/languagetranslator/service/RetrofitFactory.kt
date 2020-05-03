package com.example.languagetranslator.service

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {
    const val BASE_URL = "http://8b9d561e.ngrok.io"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun makeRetrofitService(): apiservice {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(apiservice::class.java)
    }
}