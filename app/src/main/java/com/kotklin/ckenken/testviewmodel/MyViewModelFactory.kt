package com.kotklin.ckenken.testviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object MyViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance()
    }
}