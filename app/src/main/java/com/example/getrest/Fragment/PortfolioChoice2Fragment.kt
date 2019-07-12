package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Adapter.ResumeQuestionChoiceRecyclerViewAdapter
import com.example.getrest.Data.PortfolioOverviewData

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio_choice2.*


class PortfolioChoice2Fragment : Fragment() {

    lateinit var resumeQuestionChoiceRecyclerViewAdapter: ResumeQuestionChoiceRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio_choice2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<PortfolioOverviewData> = ArrayList()
//        dataList.add(PortfolioOverviewData(
//            "솝트", "2018.09", "2019.07"))
//        dataList.add(PortfolioOverviewData(
//            "KB DNA", "2019.05", "2019.09"))
//        dataList.add(PortfolioOverviewData(
//            "전주국제영화제", "2019.03", "2019.07"))
//        dataList.add(PortfolioOverviewData(
//            "이끼", "2018.03", "2018.12"))
//        dataList.add(PortfolioOverviewData(
//            "노다지", "2018.03", "2018.07"))
//        dataList.add(PortfolioOverviewData(
//            "인라이튼", "2019.03", "2019.07"))

        resumeQuestionChoiceRecyclerViewAdapter = ResumeQuestionChoiceRecyclerViewAdapter(context!!, dataList)
        rv_portfolio_overview.adapter = resumeQuestionChoiceRecyclerViewAdapter
        rv_portfolio_overview.layoutManager = LinearLayoutManager(context!!)
    }



}
