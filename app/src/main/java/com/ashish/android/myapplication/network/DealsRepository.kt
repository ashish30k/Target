package com.ashish.android.myapplication.network

import com.ashish.android.myapplication.model.DealList
import io.reactivex.Observable

class DealsRepository(private val targetAPIService: TargetAPIService) {
    fun getDeals(): Observable<DealList> {
        return targetAPIService.getDeals()
    }
}