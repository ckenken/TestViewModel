package com.kotklin.ckenken.testviewmodel.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(val repository: DataRepository, application: Application) : ViewModel() {
    val comments = liveData {
        emit(repository.getCommentContents())
    }

    fun insertComment(content: String, title: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertContent(content, title)
    }

    fun clearAll() = viewModelScope.launch {
        repository.clearAll()
    }
}