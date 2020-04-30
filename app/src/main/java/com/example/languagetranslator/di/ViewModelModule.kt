package com.example.languagetranslator.di

import androidx.lifecycle.ViewModel
import com.example.languagetranslator.presenter.NewWordViewModel
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