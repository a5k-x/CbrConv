package com.light.cbrconv.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.light.cbrconv.databinding.ItemBinding
import com.light.cbrconv.model.data.Aui


class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = listOf<Aui>()

    fun init(list: List<Aui>) {
        this.dataList = list
    }


    inner class ViewHolder(private val vb: ItemBinding) : RecyclerView.ViewHolder(vb?.root) {
        fun bind(position: Int) {
            vb?.nameValute.text = dataList[position].getName()
            vb?.charCode.text = dataList[position].getChatrCode()
            vb?.nominal.text ="Номинал: "+ dataList[position].getNominal().toString()
            vb?.value.text ="Курс: " +dataList[position].getvalue().toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount() = dataList.size

}