package com.ashish.android.myapplication.view

import android.content.Context
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.model.Deal

class DealDetailsUIModel(val context: Context?, val deal: Deal?) {
    fun getRegularPrice(): String {
        val regularPriceLabelString = context?.getString(R.string.regular_price_label) + " "
        return regularPriceLabelString + deal?.price
    }

    fun getRegularPriceLabel() = context?.getString(R.string.regular_price_label) + " "
}