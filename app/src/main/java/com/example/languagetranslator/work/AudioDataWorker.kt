package com.example.languagetranslator.work

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.VowelRepository
import com.example.languagetranslator.presenter.VowelVIewModel
import com.example.languagetranslator.service.Network
import javax.inject.Inject


class AudioDataWorker(vowelRepository: VowelRepository,networkService: Network,appContext: Context, params: WorkerParameters) : Worker(appContext, params)  {

    override fun doWork(): Result {
        return Result.success()
    }

    class Factory @Inject constructor(
        private val vowelRepository: VowelRepository,
        private val network: Network
        ): ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): Worker {
            return AudioDataWorker(vowelRepository,network, appContext, params)
        }
    }
}