package com.kotklin.ckenken.testviewmodel.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataViewModelFactory(private val dataRepository: DataRepository, private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            return DataViewModel(dataRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}