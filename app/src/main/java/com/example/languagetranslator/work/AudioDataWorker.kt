package com.example.languagetranslator.work

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.languagetranslator.model.Vowels
import com.example.languagetranslator.presenter.VowelRepository
import com.example.languagetranslator.service.Network
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.*
import javax.inject.Inject


class AudioDataWorker(val vowelRepository: VowelRepository,val networkService: Network,val appContext: Context, params: WorkerParameters) : Worker(appContext, params)  {

    private var count : Int = 0
    override fun doWork(): Result {
        Log.e("People" , "This is happening ")

        getVowels()

        return Result.success()
    }
    @Synchronized
    private fun getAudio(filename: String , index : Int) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            networkService.getAudios(filename).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it->  writeResponseBodyToDisk(it , index , filename)
                }, {
                    it -> Log.e("Error", "We got an error ${it.message}")
                }))

    }

    private fun getVowels()
    {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                networkService.getVowellData()
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

    }
    private fun onFailure(t: Throwable) {
        Log.e("GUYS", "Imagine I did not make it , I am sad ${t.message}")
    }

    private fun onResponse(response: List<Vowels>) {
        count = response.size-1
        val completable = vowelRepository.insertAll(response)
        completable.subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    response.forEachIndexed {index , vowel ->
                        getAudio(vowel.filename , index)
                    }
                }
                override fun onError(e: Throwable) {
                    Log.e("Error","Ma guys I encountered an error while writting to the dtabase ${e.message}")
                }

            });

    }
    private fun writeResponseBodyToDisk(body: ResponseBody , index: Int , filename: String): Boolean {
        return try {
            val audiofile = File(Environment.getExternalStorageDirectory(), filename)
            Log.e("INDEX","This is the index ${index}")
            var inputStream: InputStream? = null

            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body.contentLength()

                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()

                outputStream = FileOutputStream(audiofile)

                while (true) {

                    val read: Int = inputStream.read(fileReader)

                    if (read == -1) {
                        break
                    }
                    outputStream?.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()
                }
                outputStream?.flush()

                true
            } catch (e: IOException) {
                false

            } finally {

                inputStream?.close()

                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
        finally {
            if(index == 50)
            {
                Log.e("FInalizing","We is finalizing")
                val local = Intent()
                local.action = "com.hello.action"
                appContext.sendBroadcast(local)
            }
        }
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