package com.example.languagetranslator.presenter

import androidx.lifecycle.ViewModel
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