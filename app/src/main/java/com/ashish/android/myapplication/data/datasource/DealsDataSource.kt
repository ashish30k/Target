package com.ashish.android.myapplication.data.datasource

import android.util.Log
import com.ashish.android.myapplication.data.TargetAPIService
import com.ashish.android.myapplication.data.db.DealDao
import com.ashish.android.myapplication.model.Deal
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DealsDataSource @Inject constructor(
    private val targetAPIService: TargetAPIService,
    private val dealDao: DealDao
) : DealsRepository {

    override fun getDeals(): Observable<List<Deal>> {
        return Observable.concatArray(getDealsFromRemote(), getDealsFromDB())
    }

    fun getDealsFromDB(): Observable<List<Deal>> {
        return dealDao.getAllDeals()
            .filter { it.isNotEmpty() }
            .onErrorReturnItem(listOf())
            .doOnNext {
                Log.e(DealsDataSource::class.simpleName, "getting deals from db")
            }
    }

    fun getDealsFromRemote(): Observable<List<Deal>> {
        return targetAPIService.getDeals().map { it.deals }
            .filter { it.isNotEmpty() }
            .onErrorResumeNext(getDealsFromDB())
            .doOnNext {
                Log.e(DealsDataSource::class.simpleName, "getting deals from remote")
                storeDealsInDB(it)
            }
    }

    private fun storeDealsInDB(dealsList: List<Deal>) {
        Observable.fromCallable { dealDao.insertDeals(dealsList) }
            .onErrorReturnItem(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { Log.e(DealsDataSource::class.simpleName, "storing deals in db") }
    }
}