package com.ashish.android.myapplication.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.model.Deal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_deal_details.view.*
import kotlinx.android.synthetic.main.tool_bar.view.*

class DealDetailFragment : Fragment() {
    private var deal: Deal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_DEAL)) {
                deal = it.getParcelable(ARG_DEAL)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_deal_details, container, false)

        deal?.let {
            rootView.my_toolbar.title = it.title
            Picasso.get().load(it.image).into(rootView.deal_details_image_view)
            rootView.deal_details_title_text_view.text = it.title
            rootView.deal_details_description_text_view.text = it.description

            if (it.salePrice != null && it.price != null) {
                rootView.deal_details_sales_price_text_view.text = it.salePrice

                val regularPriceLabelString = getString(R.string.regular_price_label) + " "
                val regularPrice = regularPriceLabelString + it.price
                rootView.deal_details_regular_price_text_view.setText(regularPrice, TextView.BufferType.SPANNABLE)
                val spannable = rootView.deal_details_regular_price_text_view.text as Spannable
                spannable.setSpan(
                    StrikethroughSpan(),
                    regularPriceLabelString.length,
                    regularPrice.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (it.salePrice == null && it.price != null) {
                rootView.deal_details_sales_price_text_view.text = it.price
                rootView.deal_details_sales_price_text_view.setTextColor(Color.DKGRAY)
            } else if (it.salePrice != null && it.price == null) {
                rootView.deal_details_sales_price_text_view.text = it.salePrice
                rootView.deal_details_sales_price_text_view.setTextColor(Color.DKGRAY)
            }
        }
        return rootView
    }

    companion object {
        const val ARG_DEAL = "deal"
    }
}
