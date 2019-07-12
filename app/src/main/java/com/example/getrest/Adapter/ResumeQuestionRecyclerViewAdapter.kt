package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.getrest.Activity.ResumeQuestionChoiceActivity
import com.example.getrest.Data.ResumeQuestionData
import com.example.getrest.R
import org.jetbrains.anko.startActivity

class ResumeQuestionRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<ResumeQuestionData>): RecyclerView.Adapter<ResumeQuestionRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.rv_resumequestion, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.resume_q_item.text = "문항" + dataList[position].questionNum.toString()
        holder.resume_q_content.text = dataList[position].questionTitle

        holder.plus.setOnClickListener {
            ctx.startActivity<ResumeQuestionChoiceActivity>()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var resume_q_item = itemView.findViewById(R.id.txt_rv_resumequestion_num) as TextView
        var resume_q_content = itemView.findViewById(R.id.txt_rv_resumequestion_thesis) as TextView
        var plus = itemView.findViewById(R.id.rl_rv_resumequestion_plusbutton) as RelativeLayout
    }
}