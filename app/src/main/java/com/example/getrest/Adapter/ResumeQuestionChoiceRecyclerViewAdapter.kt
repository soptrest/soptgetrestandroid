package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.getrest.Data.PortfolioOverviewData
import com.example.getrest.R

class ResumeQuestionChoiceRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<PortfolioOverviewData>): RecyclerView.Adapter<ResumeQuestionChoiceRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.rv_resume_question_choice, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].portfolioTitle
        holder.start_date.text = dataList[position].portfolioStartDate
        holder.expire_date.text = dataList[position].portfolioExpireDate

        holder.container.setOnClickListener {
            holder.check.isSelected = !holder.check.isSelected
            //여기서 셀렉된 순서대로 ResumeQuestionActivity로 보내기
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById(R.id.rl_rv_resume_question_choice_container) as RelativeLayout
        var check = itemView.findViewById(R.id.img_rv_resume_question_choice_checked) as ImageView
        var title = itemView.findViewById(R.id.txt_rv_resume_question_choice_title) as TextView
        var start_date = itemView.findViewById(R.id.txt_rv_resume_question_choice_start_date) as TextView
        //        var wave = itemView.findViewById(R.id.txt_rv_portfolio_overview_wave) as TextView
        var expire_date = itemView.findViewById(R.id.txt_rv_resume_question_choice_expire_date) as TextView
    }
}