package com.example.languagetranslator.presenter

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.languagetranslator.model.Vowels
import io.reactivex.Completable

@Dao
interface VowelInstanceDao {

    @Query("SELECT * FROM vowels")
    fun getAllVowels() : LiveData<List<Vowels>>

    @Insert
    fun insertVowel(vararg vowel : Vowels) : Completable

    @Insert
    fun insertAllVowels(vowel: List<Vowels>) : Completable
}