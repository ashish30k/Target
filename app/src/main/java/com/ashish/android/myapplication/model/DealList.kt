package com.ashish.android.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DealList(@SerializedName("data") val deals: List<Deal>) : Parcelable