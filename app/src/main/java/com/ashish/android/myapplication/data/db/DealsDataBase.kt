package com.ashish.android.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.android.myapplication.model.Deal

@Database(entities = arrayOf(Deal::class), version = 1)
abstract class DealsDataBase : RoomDatabase() {
    abstract fun getDealDao(): DealDao
}
