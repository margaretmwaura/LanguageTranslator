package com.example.languagetranslator.presenter

import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.languagetranslator.model.Vowels
import com.example.languagetranslator.service.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.*
import javax.inject.Inject


class VowelVIewModel @Inject constructor(val network : Network):  ViewModel(){
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var vowels = MutableLiveData<List<Vowels>>()

    private val _vowels : LiveData<List<Vowels>>
         get() = vowels

    init {
        getAllTheVowels()
        getAudio()
    }

    private fun getAllTheVowels()
    {
        coroutineScope.launch {

            Log.e("We","We is doing this people")
            var results = network.getVoewlData()

            try {
                 val data = results.await()
                Log.d("Data","Here is the data my people $data")
                 vowels.postValue(data)
            }catch (e : Exception)
            {
                 Log.e("Babes","Babes there was an error ${e.message}")
            }
        }
    }

    private fun getAudio()
    {
        coroutineScope.launch {
            var audios = network.getAudios()

            try
            {
                val data = audios.await()
                writeResponseBodyToDisk(data)

                Log.d("Data","Here is the data my people ${data.byteStream()}")

            }catch (e : Exception)
            {
                Log.e("Babes","Babes there was an error while getting audio${e.message}")
            }
        }
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
//            (getExternalFilesDir(null) + File.separator.toString() +
            val futureStudioIconFile = File(Environment.getExternalStorageDirectory() ,  "audio.mp3")
            Log.e("SITE","Where the files are being saved ${Environment.getExternalStorageDirectory()}")
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)
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
                Log.e("People","There was an error while trying to download the file one ${e.message}" )
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            Log.e("People","There was an error while trying to download the file two ${e.message}" )
            false
        }
    }
}