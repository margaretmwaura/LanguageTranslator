package com.example.languagetranslator

import androidx.room.*

@Dao
interface WordInstanceDao {
    @Query("SELECT * FROM word_instance")
    fun getAll(): List<WordInstance>

    @Query("SELECT * FROM word_instance WHERE name_in_english LIKE :name_in_english")
    fun findByTitle(name_in_english: String): WordInstance

    @Insert
    fun insertAll(vararg word_instance: WordInstance)

    @Delete
    fun delete(todo: WordInstance)
}