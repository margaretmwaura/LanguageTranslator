package com.example.languagetranslator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    ViewModelFactoryModule::class,
    NewWorkModule::class
])
interface AppComponent : AndroidInjector<MyApplication>
{

    override fun inject(myApplication: MyApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}