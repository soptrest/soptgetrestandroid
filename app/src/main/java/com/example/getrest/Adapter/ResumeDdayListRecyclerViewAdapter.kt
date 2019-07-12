package com.example.getrest.Adapter

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.getrest.Activity.ResumeReadActivity
import com.example.getrest.Data.ResumeDdayListData
import com.example.getrest.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

class ResumeDdayListRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<ResumeDdayListData>): RecyclerView.Adapter<ResumeDdayListRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder{
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_resumedday_list, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int){
        if(dataList[position].leftDate == 0 || dataList[position].leftDate == 1 || dataList[position].leftDate == 2 || dataList[position].leftDate == 3){ //급할때
            holder.dday.setTextColor(Color.parseColor("#bf5959"))
            holder.dday.text = "D-"+dataList[position].leftDate.toString()
        }
        else if(dataList[position].leftDate == -1){ //마감일때
            holder.dday.text = "마감"
            holder.dday.setTextColor(Color.parseColor("#999999"))
            holder.dday_company.setTextColor(Color.parseColor("#999999"))
            holder.dday_career.setTextColor(Color.parseColor("#999999"))
            holder.line.setBackgroundColor(Color.parseColor("#999999"))
        }
        else{ //standard
            holder.dday.text = "D-"+dataList[position].leftDate.toString()
            holder.dday.setTextColor(Color.parseColor("#82bf59"))
        }

        //holder.dday.text = "D-"+dataList[position].leftDate.toString()
        holder.dday_company.text = dataList[position].companyName
        holder.dday_career.text = dataList[position].recruitJobType


        holder.container.setOnClickListener {
            ctx.startActivity<ResumeReadActivity>(
                "resumeIdx" to dataList[position].resumeIdx)
        }

    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.ll_rv_resumedday_list) as LinearLayout
        var dday = itemView.findViewById(R.id.txt_rv_resumedday_list_dday) as TextView
        var line = itemView.findViewById(R.id.resume_line) as View
//        var writeFinished = itemView.findViewById(R.id.txt_rv_resumedday_list_writefinished) as TextView
        var dday_company = itemView.findViewById(R.id.txt_rv_resumedday_list_company) as TextView
        var dday_career = itemView.findViewById(R.id.txt_rv_resumedday_list_career) as TextView
        //var dday_check = itemView.findViewById(R.id.img_rv_resumedday_check) as ImageView
    }
}