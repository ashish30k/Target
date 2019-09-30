package com.ashish.android.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashish.android.myapplication.network.DealsRepository

class DealsViewModelFactory(private val dealsRepository: DealsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DealsViewModel::class.java)) {
            return DealsViewModel(dealsRepository) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}