package com.ashish.android.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Deal(
    val title: String?,
    val description: String?,
    val image: String?,
    val salePrice: String?,
    val price: String?,
    val guid: String,
    val aisle: String?,
    val index: Int?,
    @SerializedName("_id")
    val id: String
) : Parcelable {
}