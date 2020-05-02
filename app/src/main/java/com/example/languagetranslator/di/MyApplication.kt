package com.example.languagetranslator.di

import android.app.Activity
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.languagetranslator.work.AudioWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MyApplication : Application() , HasActivityInjector
{
    //This is so that I can use dagger in my application
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var myWorkerFactory: AudioWorkerFactory

    override fun onCreate()
    {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(myWorkerFactory)
                .build()
        )
    }

}