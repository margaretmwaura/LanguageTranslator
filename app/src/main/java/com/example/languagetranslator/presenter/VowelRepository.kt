package com.example.languagetranslator.presenter

import androidx.lifecycle.LiveData
import com.example.languagetranslator.model.Vowels
import io.reactivex.Completable
import javax.inject.Inject

class VowelRepository constructor(private val vowelInstanceDao: VowelInstanceDao) {

    val allVowels: List<Vowels> = vowelInstanceDao.getAllVowels()

    fun insert(vowels: Vowels) : Completable {
        return vowelInstanceDao.insertVowel(vowels)
    }

    fun insertAll(vowel_list : List<Vowels>) : Completable {

        return vowelInstanceDao.insertAllVowels(vowel_list)
    }
}