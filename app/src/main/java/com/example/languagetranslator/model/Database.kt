package com.example.languagetranslator.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.languagetranslator.presenter.VowelInstanceDao
import com.example.languagetranslator.presenter.WordInstanceDao

@Database(entities = [WordInstance::class, Vowels::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordInstanceDao(): WordInstanceDao
    abstract fun vowelInstanceDao() : VowelInstanceDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "word_instance.db")
            .build()
    }
}