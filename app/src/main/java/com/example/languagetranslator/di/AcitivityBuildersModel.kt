package com.example.languagetranslator.di

import com.example.languagetranslator.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [NewWordViewModelModule::class])
    internal abstract fun contributeAuthActivity(): NewWord

    @ContributesAndroidInjector(modules = [NewWordViewModelModule::class])
    internal abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector(modules = [NewWordViewModelModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [VowelViewModelModule::class])
    internal abstract fun contributeAlphabets(): AlphabetsFragment
}