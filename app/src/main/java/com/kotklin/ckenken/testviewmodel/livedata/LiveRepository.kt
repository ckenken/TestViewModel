package com.kotklin.ckenken.testviewmodel.livedata

import androidx.lifecycle.MutableLiveData

class LiveRepository {
    companion object{
        private var data = MutableLiveData<String>()
    }

    init {
        data.value= "init"
    }

    fun saveText(text: String){
        data.value= text
    }

    fun getText() = data
}