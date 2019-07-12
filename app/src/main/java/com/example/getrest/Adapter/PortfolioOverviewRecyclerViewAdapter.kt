package com.example.getrest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.getrest.Activity.ViewPortActivity
import com.example.getrest.Data.PortfolioOverviewData
import com.example.getrest.Fragment.Portfolio1Fragment
import com.example.getrest.R
import org.jetbrains.anko.startActivity

class PortfolioOverviewRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<PortfolioOverviewData>): RecyclerView.Adapter<PortfolioOverviewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(com.example.getrest.R.layout.rv_portfolio_overview, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].portfolioImg).into(holder.thumbnail)
           holder.title.text = dataList[position].portfolioTitle
           holder.start_date.text = dataList[position].portfolioStartDate
           holder.expire_date.text = dataList[position].portfolioExpireDate

           holder.container.setOnClickListener {
               ctx.startActivity<ViewPortActivity>(
                   "portfolioIdx" to dataList[position].portfolioIdx
                   )

           }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById(R.id.rl_rv_portfolio_product_overview_container) as RelativeLayout
        var title = itemView.findViewById(com.example.getrest.R.id.txt_rv_portfolio_overview_title) as TextView
        var start_date = itemView.findViewById(com.example.getrest.R.id.txt_rv_portfolio_overview_start_date) as TextView
        var wave = itemView.findViewById(R.id.txt_rv_portfolio_overview_wave) as TextView
        var expire_date = itemView.findViewById(R.id.txt_rv_portfolio_overview_expire_date) as TextView
        var thumbnail = itemView.findViewById(R.id.img_rv_porfolio_overview_thumbnail) as ImageView
    }
}