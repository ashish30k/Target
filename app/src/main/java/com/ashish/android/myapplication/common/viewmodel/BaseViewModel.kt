package com.ashish.android.myapplication.common.viewmodel

import androidx.lifecycle.ViewModel
import com.ashish.android.myapplication.common.dagger.DaggerViewModelInjector
import com.ashish.android.myapplication.common.dagger.NetworkModule
import com.ashish.android.myapplication.common.dagger.ViewModelInjector
import com.ashish.android.myapplication.viewmodel.DealsViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is DealsViewModel -> injector.inject(this)
        }
    }
}