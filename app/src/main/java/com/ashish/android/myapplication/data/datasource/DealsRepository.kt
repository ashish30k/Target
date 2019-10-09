package com.ashish.android.myapplication.data.datasource

import com.ashish.android.myapplication.model.Deal
import io.reactivex.Observable

interface DealsRepository {
    fun getDeals(): Observable<List<Deal>>
}