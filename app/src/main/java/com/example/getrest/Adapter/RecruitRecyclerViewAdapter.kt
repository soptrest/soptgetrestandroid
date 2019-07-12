package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.getrest.Activity.RecruitDetailActivity
import com.example.getrest.Data.RecruitData
import com.example.getrest.R
import org.jetbrains.anko.startActivity

class RecruitRecyclerViewAdapter (val ctx: Context, var dataList: ArrayList<RecruitData>): RecyclerView.Adapter<RecruitRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_recruit, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].companyImage).into(holder.img_thumbnail)
        holder.company.text = dataList[position].companyName
        holder.job.text = dataList[position].recruitJobType
        holder.deadline.text = dataList[position].recruitExpireDate

        holder.container.setOnClickListener{
         ctx.startActivity<RecruitDetailActivity>(
             "recruitIdx" to dataList[position].recruitIdx,
             "companyName" to dataList[position].companyName)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.ll_rv_item_recruit_overview_container_real) as LinearLayout
        var img_thumbnail = itemView.findViewById(R.id.img_rv_item_recruit_overview_thumbnail_real) as ImageView
        var company = itemView.findViewById(R.id.txt_rv_item_recruit_overview_company_real) as TextView
        var job = itemView.findViewById(R.id.txt_rv_item_recruit_overview_task_real) as TextView
        var deadline = itemView.findViewById(R.id.txt_rv_item_recruit_overview_deadline_real) as TextView
    }
}