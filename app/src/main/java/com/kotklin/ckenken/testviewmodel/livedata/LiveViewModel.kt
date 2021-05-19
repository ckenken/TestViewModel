package com.kotklin.ckenken.testviewmodel.livedata

import androidx.lifecycle.ViewModel

class LiveViewModel: ViewModel() {
    private val repository = LiveRepository()

    fun saveText(text: String){
        repository.saveText(text)
    }

    fun getText() = repository.getText()
}
