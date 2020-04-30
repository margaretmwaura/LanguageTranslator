package com.example.languagetranslator.di

import androidx.lifecycle.ViewModel
import com.example.languagetranslator.presenter.NewWordViewModel
import com.example.languagetranslator.service.Network
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NewWorkModule {

    @Provides
    @Singleton
    fun provideNetworkClass(): Network
    {
        return Network()
    }

}