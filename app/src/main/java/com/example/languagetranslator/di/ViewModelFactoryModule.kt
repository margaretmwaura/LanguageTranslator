package com.example.languagetranslator.di

import androidx.lifecycle.ViewModelProvider
import com.example.languagetranslator.presenter.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
//
//    @Provides
//    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory
//    {
//        return ViewModelFactory(providerMap)
//    }

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}