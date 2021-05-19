package com.kotklin.ckenken.testviewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun saveText(text: String){
        repository.saveText(text)
    }
}