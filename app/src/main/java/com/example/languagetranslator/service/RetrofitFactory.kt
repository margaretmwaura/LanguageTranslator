package com.example.languagetranslator.service

import com.google.gson.GsonBuilder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {
    const val BASE_URL = "http://626c447286d4.ngrok.io"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun makeRetrofitService(): apiservice {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(apiservice::class.java)
    }
}