package com.example.languagetranslator.presenter

import androidx.lifecycle.LiveData
import com.example.languagetranslator.model.WordInstance
import io.reactivex.Completable

class NewWordRepository(private val wordDao: WordInstanceDao) {

    val allWords: LiveData<List<WordInstance>> = wordDao.getAll()

    fun insert(word: WordInstance) : Completable {
       return wordDao.insertAll(word)
    }
}