package com.example.languagetranslator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vowels")
data class Vowels(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "filename") var filename: String
)