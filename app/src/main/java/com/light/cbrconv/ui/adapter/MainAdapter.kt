package com.light.cbrconv.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.light.cbrconv.databinding.ItemBinding
import com.light.cbrconv.domain.entity.Currency

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val FIELD_NOMINAL = "Номинал: "
        const val FIELD_WELL = "Курс: "
    }

    private var dataList = emptyList<Currency>()

    @SuppressLint("NotifyDataSetChanged")
    fun init(list: List<Currency>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

}

class ViewHolder(private val vb: ItemBinding) : RecyclerView.ViewHolder(vb.root) {
    fun bind(item: Currency) {
        vb.nameValute.text = item.name
        vb.charCode.text = item.charCode
        vb.nominal.text = MainAdapter.FIELD_NOMINAL + item.nominal.toString()
        vb.value.text = MainAdapter.FIELD_WELL + item.value.toString()
    }

}