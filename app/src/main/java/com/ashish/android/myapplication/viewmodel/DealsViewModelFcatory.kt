package com.ashish.android.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashish.android.myapplication.data.datasource.DealsDataSource
import javax.inject.Inject

class DealsViewModelFcatory @Inject constructor(private val dealsDataSource: DealsDataSource) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DealsViewModel::class.java)) {
            DealsViewModel(this.dealsDataSource) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}