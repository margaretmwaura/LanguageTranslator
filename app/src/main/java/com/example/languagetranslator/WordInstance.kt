package com.example.languagetranslator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_instance")
data class WordInstance(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name_in_english") var name_in_english: String,
    @ColumnInfo(name = "name_in_your_language") var name_in_your_language: String
)