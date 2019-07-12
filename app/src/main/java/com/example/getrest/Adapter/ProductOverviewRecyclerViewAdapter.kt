package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.getrest.Activity.ViewActivity
import com.example.getrest.Data.ProductOverviewData
import com.example.getrest.R
import org.jetbrains.anko.startActivity


class ProductOverviewRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ProductOverviewData>): RecyclerView.Adapter<ProductOverviewRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        var view: View = LayoutInflater.from(ctx).inflate(com.example.getrest.R.layout.rv_home_item_product_overview, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].portfolioTitle
        holder.start_date.text = dataList[position].portfolioStartDate
        holder.expire_date.text = dataList[position].portfolioExpireDate

        holder.container.setOnClickListener {
            holder.container.isSelected =! holder.container.isSelected
            holder.title.isSelected =! holder.title.isSelected
            holder.start_date.isSelected =! holder.start_date.isSelected
            holder.wave.isSelected =! holder.wave.isSelected
            holder.expire_date.isSelected =! holder.expire_date.isSelected
            holder.button.isSelected =! holder.button.isSelected

            ctx.startActivity<ViewActivity>()
        }
    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.rl_rv_home_item_product_overview_container) as RelativeLayout
        var title = itemView.findViewById(com.example.getrest.R.id.txt_rv_home_item_product_overview_title) as TextView
        var start_date = itemView.findViewById(com.example.getrest.R.id.txt_rv_home_item_product_overview_start_date) as TextView
        var wave = itemView.findViewById(R.id.txt_rv_home_item_product_overview_wave) as TextView
        var expire_date = itemView.findViewById(R.id.txt_rv_home_item_product_overview_expire_date) as TextView
        var button = itemView.findViewById(com.example.getrest.R.id.img_rv_home_item_product_overview) as ImageView
    }




}