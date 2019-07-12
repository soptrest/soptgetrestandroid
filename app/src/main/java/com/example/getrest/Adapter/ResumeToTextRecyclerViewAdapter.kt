package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.getrest.Data.ResumeToTextData
import com.example.getrest.R

class ResumeToTextRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ResumeToTextData>): RecyclerView.Adapter<ResumeToTextRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.rv_resumequestion_totext, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.num.text = dataList[position].num
        holder.thesis.text = dataList[position].thesis
        holder.selected_port_title.text = dataList[position].selected_port_title
        holder.selected_port_content.text = dataList[position].selected_port_content

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num = itemView.findViewById(R.id.txt_rv_resumequestion_totext_num) as TextView
        var thesis = itemView.findViewById(R.id.txt_rv_resumequestion_totext_thesis) as TextView
        var selected_port_title = itemView.findViewById(R.id.txt_rv_resumequestion_totext_title) as TextView
        var selected_port_content = itemView.findViewById(R.id.txt_rv_resumequestion_totext_content) as TextView
    }
}