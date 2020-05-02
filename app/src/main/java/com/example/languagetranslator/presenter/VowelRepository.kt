package com.example.languagetranslator.presenter

import androidx.lifecycle.LiveData
import com.example.languagetranslator.model.Vowels
import io.reactivex.Completable

class VowelRepository(private val wordDao: WordInstanceDao) {

    val allWords: LiveData<List<Vowels>> = wordDao.getAllVowels()

    fun insert(vowels: Vowels) : Completable {
        return wordDao.insertVowel(vowels)
    }

    fun insertAll(vowel_list : List<Vowels>) : Completable {

        return wordDao.insertAllVowels(vowel_list)
    }
}