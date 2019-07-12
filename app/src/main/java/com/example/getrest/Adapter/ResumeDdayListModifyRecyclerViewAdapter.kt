package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.getrest.Data.ResumeDdayListModifyData
import com.example.getrest.R

class ResumeDdayListModifyRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<ResumeDdayListModifyData>): RecyclerView.Adapter<ResumeDdayListModifyRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_resumedday_list_modify, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int){
        holder.dday.text = dataList[position].dday
//        holder.writeFinished.text = dataList[position].writeFinished
        holder.dday_company.text = dataList[position].dday_company
        holder.dday_career.text = dataList[position].dday_career

        holder.container.setOnClickListener {
            holder.dday_check.isSelected = !holder.dday_check.isSelected
            if(holder.dday_check.isSelected) {
                val idx = dataList.indexOf(dataList[position])
                Toast.makeText(ctx, "${idx}", Toast.LENGTH_SHORT).show()

            }
        }

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.ll_rv_resumedday_list_modify) as LinearLayout
        var dday = itemView.findViewById(R.id.txt_rv_resumedday_list_modify_dday) as TextView
//        var writeFinished = itemView.findViewById(R.id.txt_rv_resumedday_list_modify_writefinished) as TextView
        var dday_company = itemView.findViewById(R.id.txt_rv_resumedday_list_modify_company) as TextView
        var dday_career = itemView.findViewById(R.id.txt_rv_resumedday_list_modify_career) as TextView
        var dday_check = itemView.findViewById(R.id.img_rv_resumedday_modify_check) as ImageView
    }
}