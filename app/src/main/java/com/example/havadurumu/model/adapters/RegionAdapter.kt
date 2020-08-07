package com.example.havadurumu.model.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.havadurumu.R
import com.example.havadurumu.model.countries.RegionResponse

class RegionAdapter(val context: Context, private val list: List<RegionResponse>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_spinner_layout, p2, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvItemText)

        tvTitle.text = list[p0].region

        return view
    }

    override fun getItem(p0: Int): Any {
        return p0.toString()
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}