package com.kotklin.ckenken.testviewmodel

import androidx.lifecycle.ViewModel

class ResultViewModel: ViewModel() {
    private val repository = MainRepository()
    fun getAllText() = repository.getAllText()
}