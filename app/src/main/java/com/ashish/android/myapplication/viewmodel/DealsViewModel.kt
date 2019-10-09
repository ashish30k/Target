package com.ashish.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.android.myapplication.data.datasource.DealsDataSource
import com.ashish.android.myapplication.model.Deal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DealsViewModel(val dealsDataSource: DealsDataSource) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var _dealsLiveData = MutableLiveData<List<Deal>>()
    val dealsLiveData: LiveData<List<Deal>> = _dealsLiveData

    private var _noDealAvailableLiveData = MutableLiveData<Boolean>()
    val noDealAvailableLiveData: LiveData<Boolean> = _noDealAvailableLiveData

    private var _dealsErrorLiveData = MutableLiveData<String>()
    val dealsErrorLiveData: LiveData<String> = _dealsErrorLiveData

    private var _showProgressBarLiveData = MutableLiveData<Boolean>()
    val showProgressBarLiveData: LiveData<Boolean> = _showProgressBarLiveData

    fun fetchDeals() {
        compositeDisposable.add(dealsDataSource.getDeals()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { _showProgressBarLiveData.postValue(true) }
            .doAfterTerminate { _showProgressBarLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    deals ->
                _showProgressBarLiveData.postValue(false)
                if (deals.size == 0) {
                    _noDealAvailableLiveData.postValue(true)
                } else {
                    _dealsLiveData.postValue(deals)
                }

            }, { throwable ->
                _showProgressBarLiveData.postValue(false)
                _dealsErrorLiveData.postValue(throwable.message)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}