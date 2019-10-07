package com.example.unitconverter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unitconverter.R
import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.MainItemClickListener

class DimensionAdapter(
    private val conversion: Array<Conversion>,
    private val mainItemClickListener: MainItemClickListener
) :
    RecyclerView.Adapter<DimensionAdapter.DimensionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DimensionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dimantion_text_view, parent, false)
        return DimensionViewHolder(view, mainItemClickListener)
    }

    override fun getItemCount(): Int {
        return conversion.size
    }

    override fun onBindViewHolder(holder: DimensionViewHolder, position: Int) {
        holder.bind(conversion[position])
    }


    class DimensionViewHolder(itemView: View, private val mainItemClickListener: MainItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val dimension: TextView = itemView.findViewById(R.id.dimension_tv)
        fun bind(conversion: Conversion) {
            dimension.text = itemView.resources.getString(conversion.description)
            itemView.setOnClickListener { mainItemClickListener.setMainClickListener(conversion) }
        }
    }
}