package com.ashish.android.myapplication.common

import android.app.Application
import com.ashish.android.myapplication.common.dagger.AppModule
import com.ashish.android.myapplication.common.dagger.DaggerAppComponent
import com.ashish.android.myapplication.common.dagger.NetworkModule
import com.ashish.android.myapplication.common.dagger.RoomModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}