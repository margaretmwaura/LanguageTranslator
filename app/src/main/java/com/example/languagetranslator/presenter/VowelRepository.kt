package com.example.languagetranslator.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.languagetranslator.model.Vowels
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VowelRepository @Inject constructor(val vowelInstanceDao: VowelInstanceDao) {

    val allVowels: List<Vowels> = vowelInstanceDao.getAllVowels()

    fun insert(vowels: Vowels) : Completable {
        return vowelInstanceDao.insertVowel(vowels)
    }

    fun insertAll(vowel_list : List<Vowels>) : Completable {

        Log.d("VowelList","Here is the damn list ${vowel_list}");

        return vowelInstanceDao.insertAllVowels(vowel_list)
    }

    companion object {

        const val PAGE_SIZE = 100

        // For Singleton instantiation
        @Volatile
        private var instance: VowelRepository? = null

        fun getInstance(dao: VowelInstanceDao) =
            instance ?: synchronized(this) {
                instance
                    ?: VowelRepository(dao).also { instance = it }
            }
    }
}

