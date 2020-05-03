package com.example.languagetranslator.di

import android.app.Application
import com.example.languagetranslator.model.AppDatabase
import com.example.languagetranslator.presenter.VowelInstanceDao
import com.example.languagetranslator.presenter.VowelRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.invoke(app)

    @Singleton
    @Provides
    @Named("VowelInstanceDao")
    fun provideVowelDao(db: AppDatabase) = db.vowelInstanceDao()

    @Singleton
    @Provides
    fun provideVowelRepository(@Named("VowelInstanceDao") vowelInstanceDao: VowelInstanceDao) : VowelRepository{
        return VowelRepository(vowelInstanceDao)
    }
}