package com.example.languagetranslator.di

import androidx.work.Worker
import com.example.languagetranslator.work.AudioDataWorker
import com.example.languagetranslator.work.ChildWorkerFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out Worker>)

@Module
abstract class MyWorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(AudioDataWorker::class)
    internal abstract fun bindMyWorkerFactory(worker: AudioDataWorker.Factory): ChildWorkerFactory
}