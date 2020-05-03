package com.example.languagetranslator.di

import androidx.lifecycle.ViewModel
import com.example.languagetranslator.presenter.NewWordViewModel
import com.example.languagetranslator.presenter.VowelVIewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class NewWordViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewWordViewModel::class)
    abstract fun bindNewWordViewModel(viewModel: NewWordViewModel): ViewModel

}

@Module
abstract class VowelViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VowelVIewModel::class)
    abstract fun bindVowelViewModel(viewModel: VowelVIewModel): ViewModel

}