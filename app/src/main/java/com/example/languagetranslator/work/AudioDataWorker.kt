package com.example.languagetranslator.work

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.languagetranslator.presenter.ViewModelFactory
import com.example.languagetranslator.presenter.VowelRepository
import com.example.languagetranslator.presenter.VowelVIewModel
import com.example.languagetranslator.service.Network
import kotlinx.coroutines.sync.withLock
import okhttp3.ResponseBody
import java.io.*
import javax.inject.Inject


class AudioDataWorker(val vowelRepository: VowelRepository,val networkService: Network,appContext: Context, params: WorkerParameters) : Worker(appContext, params)  {

    override fun doWork(): Result {
        Log.e("People" , "This is happening ")

        val vowels = vowelRepository.allVowels
        Log.e("Mapeople","The vowels ${vowels}")
        vowels.forEach {
            Log.e("PPLE","Yaaasss")
            getAudio(it.filename)
        }
        return Result.success()
    }
    @Synchronized
    private fun getAudio(filename: String) {
        var audio = networkService.getAudios(filename)
        writeResponseBodyToDisk(audio)
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
//            (getExternalFilesDir(null) + File.separator.toString() +
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