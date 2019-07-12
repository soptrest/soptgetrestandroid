package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.getrest.Data.ResumeQuestionChosenData
import com.example.getrest.R

class ResumeQuestionChosenRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ResumeQuestionChosenData>): RecyclerView.Adapter<ResumeQuestionChosenRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.rv_resume_question_chosen, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.thesis.text = dataList[position].thesis
        holder.num.text = dataList[position].num

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num = itemView.findViewById(R.id.txt_rv_resume_question_chosen_num) as TextView
        var thesis = itemView.findViewById(R.id.txt_rv_resume_question_chosen_thesis) as TextView
    }

}