package com.example.languagetranslator.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.example.languagetranslator.model.AppDatabase
import com.example.languagetranslator.model.WordInstance
import io.reactivex.Completable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewWordViewModel  @Inject constructor(application: Application): AndroidViewModel(application){

    var words: LiveData<List<WordInstance>>

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val repository : NewWordRepository

    init {
        val wordsDao = AppDatabase.invoke(application).wordInstanceDao()
        repository = NewWordRepository(wordsDao)
        words = repository.allWords
    }

    fun saveNewWord(wordInstance: WordInstance) : Completable
    {
       return repository.insert(wordInstance)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}