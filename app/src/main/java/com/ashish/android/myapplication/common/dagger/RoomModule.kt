package com.ashish.android.myapplication.common.dagger

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ashish.android.myapplication.common.DATABASE_NAME
import com.ashish.android.myapplication.data.db.DealsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class RoomModule {
    private val dealsDataBase: DealsDataBase

    @Inject
    constructor(application: Application) {
        val SIMPLE_MIGRATION = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table so there's nothing else to do here.
            }
        }
        this.dealsDataBase = Room.databaseBuilder(application, DealsDataBase::class.java, DATABASE_NAME)
            .addMigrations(SIMPLE_MIGRATION)
            .build()
    }

    @Provides
    @Singleton
    fun provideDealsDataBase() = dealsDataBase

    @Provides
    @Singleton
    fun provideProductDao() = dealsDataBase.getDealDao()
}