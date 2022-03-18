package com.example.currency_app.presentation.view.currencyList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.data.entity.Currency

class CurrencyListAdapter(
    private val listener: CurrencyClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Currency>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(currencyList: ArrayList<Currency>) {
        items.clear()
        items.addAll(currencyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyListViewHolder(inflater.inflate(R.layout.currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrencyListViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    interface CurrencyClickListener {
        fun onCurrencyClick(currencyItem: Currency, itemView: View, position: Int)
    }

}