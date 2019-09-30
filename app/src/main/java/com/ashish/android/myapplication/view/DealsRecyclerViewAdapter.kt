package com.ashish.android.myapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashish.android.myapplication.R
import com.ashish.android.myapplication.model.Deal
import com.squareup.picasso.Picasso

class DealsRecyclerViewAdapter(
    val context: Context,
    var deals: MutableList<Deal>,
    val dealClickListener: ((Deal) -> Unit)
) : RecyclerView.Adapter<DealsRecyclerViewAdapter.DealViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deal_row, parent, false)
        return DealViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        holder.bind(deals[position], dealClickListener)
    }

    override fun getItemCount() = deals.size

    fun setItems(deals: MutableList<Deal>) {
        this.deals.clear()
        this.deals.addAll(deals)
        notifyDataSetChanged()
    }

    private fun bindDealPrice(holder: DealViewHolder, deal: Deal) {
        if (deal.salePrice != null) {
            holder.dealPriceTextView.text = deal.salePrice
        } else if (deal.price != null) {
            holder.dealPriceTextView.text = deal.price
        } else {
            holder.dealPriceTextView.text = context.getString(R.string.price_not_available);
        }
    }

    private fun bindDealName(holder: DealViewHolder, deal: Deal) {
        holder.dealNameTextView.text = deal.title
    }

    private fun bindDealAisleNumber(holder: DealViewHolder, deal: Deal) {
        holder.dealAisleNumberTextView.text = deal.aisle
    }

    class DealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dealImageView = itemView.findViewById<ImageView>(R.id.deal_image_view)
        val dealNameTextView = itemView.findViewById<TextView>(R.id.deal_name_textView)
        val dealPriceTextView = itemView.findViewById<TextView>(R.id.deal_price_text_view)
        val dealAisleNumberTextView = itemView.findViewById<TextView>(R.id.aisle_number_text_view)

        fun bind(deal: Deal, listener: (Deal) -> Unit) = with(itemView) {
            Picasso.get().load(deal.image).into(dealImageView)

            if (deal.salePrice != null) {
                dealPriceTextView.text = deal.salePrice
            } else if (deal.price != null) {
                dealPriceTextView.text = deal.price
            } else {
                dealPriceTextView.text = context.getString(R.string.price_not_available);
            }

            dealNameTextView.text = deal.title
            dealAisleNumberTextView.text = deal.aisle

            setOnClickListener { listener(deal) }
        }


    }
}