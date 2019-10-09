package com.ashish.android.myapplication.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ashish.android.myapplication.common.DEALS_TABLE_NAME
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = DEALS_TABLE_NAME)
data class Deal(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "salePrice") val salePrice: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "guid") val guid: String,
    @ColumnInfo(name = "aisle") val aisle: String?,
    @ColumnInfo(name = "index") val index: Int?,
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("_id")
    val id: String
) : Parcelable {
}