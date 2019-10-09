package com.ashish.android.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashish.android.myapplication.common.DEALS_TABLE_NAME
import com.ashish.android.myapplication.model.Deal
import io.reactivex.Observable

@Dao
interface DealDao {
    @Query("SELECT * from $DEALS_TABLE_NAME ORDER BY id ASC")
    fun getAllDeals(): Observable<List<Deal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeal(deal: Deal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDeals(dealsList: List<Deal>)

    @Query("DELETE FROM deals_table")
    fun delete()
}