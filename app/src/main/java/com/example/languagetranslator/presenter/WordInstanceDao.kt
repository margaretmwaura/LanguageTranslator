package com.example.languagetranslator.presenter

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.languagetranslator.model.Vowels
import com.example.languagetranslator.model.WordInstance
import io.reactivex.Completable

@Dao
interface WordInstanceDao {
    @Query("SELECT * FROM word_instance")
    fun getAll(): LiveData<List<WordInstance>>

    @Query("SELECT * FROM word_instance WHERE name_in_english LIKE :name_in_english")
    fun findByTitle(name_in_english: String): WordInstance

    @Insert
    fun insertAll(vararg word_instance: WordInstance) : Completable

    @Delete
    fun delete(todo: WordInstance)

    @Query("SELECT * FROM vowels")
    fun getAllVowels() : LiveData<List<Vowels>>

    @Insert
    fun insertVowel(vararg vowel : Vowels) : Completable

    @Insert
    fun insertAllVowels(vararg vowel_list : List<Vowels>) : Completable

}