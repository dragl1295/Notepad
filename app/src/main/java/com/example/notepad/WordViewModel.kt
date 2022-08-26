package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
private val repository:WordRepository
val allWords: LiveData<List<Word>>
 init {
     val dao = WordRoomDatabase.getDatabase(application).wordDao()
     repository = WordRepository(dao)
     allWords = repository.allWords
 }

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
    fun delete(word: Word) = viewModelScope.launch {
        repository.delete(word)
    }
}
