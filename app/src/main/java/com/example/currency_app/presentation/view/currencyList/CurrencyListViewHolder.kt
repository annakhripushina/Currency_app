package com.example.currency_app.presentation.view.currencyList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_app.R
import com.example.currency_app.data.entity.Currency
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewValue: TextView = itemView.findViewById(R.id.textViewValue)
    private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
    private val textViewCharCode: TextView = itemView.findViewById(R.id.textViewCharCode)
    private val fabConvert: FloatingActionButton = itemView.findViewById(R.id.fabConvert)
    private val imageViewUpDown: ImageView = itemView.findViewById(R.id.imageViewUpDown)

    fun bind(item: Currency, listener: CurrencyListAdapter.CurrencyClickListener) {
        val textNominalName = item.nominal.toString() + " " + item.name
        textViewValue.text = item.value.toString()
        textViewName.text = textNominalName
        textViewCharCode.text = item.charCode

        if (item.value > item.previous) {
            imageViewUpDown.setImageResource(R.drawable.ic_round_arrow_drop_up_24)
        } else if (item.value < item.previous) {
            imageViewUpDown.setImageResource(R.drawable.ic_round_arrow_drop_down_24)
        } else imageViewUpDown.isInvisible

        fabConvert.setOnClickListener {
            listener.onCurrencyClick(
                item,
                itemView,
                adapterPosition
            )
        }

    }

}