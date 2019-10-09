package com.ashish.android.myapplication.common.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val applicatoion: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = applicatoion
}
