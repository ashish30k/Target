package com.ashish.android.myapplication.network

object DealsRepositoryProvider {
    fun provideDealsRepository(targetAPIService: TargetAPIService): DealsRepository = DealsRepository(targetAPIService)
}