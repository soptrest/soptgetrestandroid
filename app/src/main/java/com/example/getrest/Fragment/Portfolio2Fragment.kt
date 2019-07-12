package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.WritePortActivity
import com.example.getrest.Activity.WritePortAddCategoryActivity
import com.example.getrest.Adapter.PortfolioOverviewRecyclerViewAdapter
import com.example.getrest.Data.PortfolioOverviewData

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio2.*
import org.jetbrains.anko.support.v4.startActivity


class Portfolio2Fragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_portfolio_record.setOnClickListener {
            startActivity<WritePortAddCategoryActivity>()
        }


    }




}
