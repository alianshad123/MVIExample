package com.anshad.mvisampleproject.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anshad.mvisampleproject.data.api.ApiHelper
import com.anshad.mvisampleproject.data.repository.MainRepository
import com.anshad.mvisampleproject.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}