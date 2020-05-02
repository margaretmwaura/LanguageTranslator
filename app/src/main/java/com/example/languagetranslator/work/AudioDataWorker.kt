package com.example.languagetranslator.work

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.VowelVIewModel
import com.example.languagetranslator.service.Network


class AudioDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params)  {
    private lateinit var vowelVIewModel: VowelVIewModel
    lateinit var mViewModelFactory : ViewModelFactory
    override suspend fun doWork(): Result {

        val vowelVIewModel = VowelVIewModel(network = Network())

        return Result.success()
    }
}