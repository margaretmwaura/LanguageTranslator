package com.example.languagetranslator.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitFactory {
    const val BASE_URL = "http://74bd453c.ngrok.io"

    fun makeRetrofitService(): apiservice {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(apiservice::class.java)
    }
}