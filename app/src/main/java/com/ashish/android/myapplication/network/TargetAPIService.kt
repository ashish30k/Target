package com.ashish.android.myapplication.network

import com.ashish.android.myapplication.model.DealList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TargetAPIService {
    @GET("api/deals")
    fun getDeals(): Observable<DealList>

    companion object Factory {
        fun create(): TargetAPIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(TargetAPIService::class.java)
        }
    }
}

const val BASE_URL = "http://target-deals.herokuapp.com/"