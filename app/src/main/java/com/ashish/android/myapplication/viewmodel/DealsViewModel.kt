package com.ashish.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.android.myapplication.model.DealList
import com.ashish.android.myapplication.network.DealsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DealsViewModel(private val dealsRepository: DealsRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var _dealsLiveData = MutableLiveData<DealList>()
    val dealsLiveData: LiveData<DealList> = _dealsLiveData

    private var _noDealAvailableLiveData = MutableLiveData<Boolean>()
    val noDealAvailableLiveData: LiveData<Boolean> = _noDealAvailableLiveData

    private var _dealsErrorLiveData = MutableLiveData<String>()
    val dealsErrorLiveData: LiveData<String> = _dealsErrorLiveData

    private var _showProgressBarLiveData = MutableLiveData<Boolean>()
    val showProgressBarLiveData: LiveData<Boolean> = _showProgressBarLiveData

    fun fetchDeals() {
        compositeDisposable.add(dealsRepository.getDeals().subscribeOn(Schedulers.io())
            .doOnSubscribe { _showProgressBarLiveData.postValue(true) }
            .doAfterTerminate { _showProgressBarLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ deals ->
                if (deals.deals.size == 0) {
                    _noDealAvailableLiveData.postValue(true)
                } else {
                    _dealsLiveData.postValue(deals)
                }

            }, { throwable ->
                _dealsErrorLiveData.postValue(throwable.message)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}