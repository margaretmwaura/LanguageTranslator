package com.example.languagetranslator.presenter

import com.example.languagetranslator.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [NewWordViewModelModule::class])
    internal abstract fun contributeAuthActivity(): NewWord
}