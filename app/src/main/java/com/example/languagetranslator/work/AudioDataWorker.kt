package com.example.languagetranslator.work

import android.content.Context
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


class AudioDataWorker(val vowelRepository: VowelRepository,val networkService: Network,appContext: Context, params: WorkerParameters) : Worker(appContext, params)  {

    override fun doWork(): Result {
        Log.e("People" , "This is happening ")

        getVowels()

        return Result.success()
    }
    @Synchronized
    private fun getAudio(filename: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            networkService.getAudios(filename).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it->  writeResponseBodyToDisk(it)
                }, {
                    it -> Log.e("Error", "We got an error")
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
        Log.e("YAAAS", "wEH the alphabets data ${response}")
        val completable = vowelRepository.insertAll(response)
        completable.subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    Log.d("MaGuys","This is what we sent to the database ${vowelRepository.allVowels}")
                    response.forEach {
                        Log.e("PPLE", "Yaaasss")
                        getAudio(it.filename)
                    }
                }
                override fun onError(e: Throwable) {
                    Log.e("Error","Ma guys I encountered an error while writting to the dtabase ${e.message}")
                }

            });

    }
    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
            val auiodfile = File(Environment.getExternalStorageDirectory(), "audio.mp3")
            Log.e(
                "SITE",
                "Where the files are being saved ${Environment.getExternalStorageDirectory()}"
            )
            var inputStream: InputStream? = null

            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body.contentLength()

                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()

                outputStream = FileOutputStream(auiodfile)

                while (true) {

                    val read: Int = inputStream.read(fileReader)

                    if (read == -1) {
                        break
                    }
                    outputStream?.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()

                    Log.d("TAG", "file download: $fileSizeDownloaded of $fileSize")
                }
                outputStream?.flush()

                true
            } catch (e: IOException) {

                Log.e(
                    "People",
                    "There was an error while trying to download the file one ${e.message}"
                )

                false

            } finally {

                inputStream?.close()

                outputStream?.close()
            }
        } catch (e: IOException) {

            Log.e("People", "There was an error while trying to download the file two ${e.message}")

            false
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