package com.example.getrest.Fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Adapter.ProductOverviewRecyclerViewAdapter
import com.example.getrest.Data.ProductOverviewData

import com.example.getrest.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_home.view.*


class HomeFragment : Fragment() {

    lateinit var productOverviewRecyclerViewAdapter: ProductOverviewRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        view.img_toolbar_home_mypage.setOnClickListener {
            addFragment(MypageFragment())
        }

        return view
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        configureRecyclerView()
        setupBarChartData()
    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<ProductOverviewData> = ArrayList()
        dataList.add(ProductOverviewData(
            "솝트", "2018.09", "2019.07"))
        dataList.add(ProductOverviewData(
            "KB DNA", "2019.05", "2019.09"))
        dataList.add(ProductOverviewData(
            "전주국제영화제", "2019.03", "2019.07"))
        dataList.add(ProductOverviewData(
            "이끼", "2018.03", "2018.12"))
        dataList.add(ProductOverviewData(
            "노다지", "2018.03", "2018.07"))
        dataList.add(ProductOverviewData(
            "인라이튼", "2019.03", "2019.07"))
        dataList.add(ProductOverviewData(
            "vivacity", "2016.09", "2017.07"))
        dataList.add(ProductOverviewData(
            "멋쟁이 사자처럼", "2016.05", "2018.09"))
        dataList.add(ProductOverviewData(
            "KUSITSM", "2019.02", "2019.06"))
        dataList.add(ProductOverviewData(
            "매쉬업", "2017.03", "2017.12"))
        dataList.add(ProductOverviewData(
            "테이브", "2017.08", "2018.01"))
        dataList.add(ProductOverviewData(
            "제천국제음악영화제", "2019.06", "2019.08"))
        dataList.add(ProductOverviewData(
            "소울라잇", "2019.02", "2019.06"))

        productOverviewRecyclerViewAdapter = ProductOverviewRecyclerViewAdapter(context!!, dataList)
        rv_home_product_overview.adapter = productOverviewRecyclerViewAdapter
        rv_home_product_overview.layoutManager = LinearLayoutManager(context!!)
    }

    private fun setupBarChartData() {
        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(1f, 2f, "1"))
        bargroup.add(BarEntry(2f, 3f, "2"))
        bargroup.add(BarEntry(3f, 5f, "3"))
        bargroup.add(BarEntry(4f, 4f, "4"))
        bargroup.add(BarEntry(5f, 7f, "5"))
        bargroup.add(BarEntry(6f, 8f, "6"))
        bargroup.add(BarEntry(7f, 10f, "7"))
        bargroup.add(BarEntry(8f, 4f, "8"))
        bargroup.add(BarEntry(9f, 1f, "9"))
        bargroup.add(BarEntry(10f, 2f, "10"))
        bargroup.add(BarEntry(11f, 5f, "11"))
        bargroup.add(BarEntry(12f, 4f, "12"))

        val xAxis = barChart.xAxis
        xAxis.setDrawLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 0.1f
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val rightYAxis = barChart.axisRight
        rightYAxis.setDrawLabels(false)
        rightYAxis.setDrawGridLines(true)
        rightYAxis.setDrawAxisLine(false)

        val leftYAxis = barChart.axisLeft
        leftYAxis.setDrawLabels(false)
        leftYAxis.setDrawGridLines(true)
        leftYAxis.setDrawAxisLine(false)

        val barDataSet = BarDataSet(bargroup, "")
        val data = BarData(barDataSet)

        barDataSet.color = ContextCompat.getColor(activity!!, android.R.color.holo_green_light)
        barDataSet.valueTextColor = ContextCompat.getColor(activity!!, android.R.color.black)
        data.barWidth = 0.5f


        barChart.invalidate()
        barChart.xAxis.labelCount = 11
        barChart.setData(data)
        barChart.setFitBars(true)
        barChart.description.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.legend.isEnabled = false
        barChart.data.setDrawValues(false)
        barChart.isDoubleTapToZoomEnabled = false
        barChart.setDrawBorders(false)
        barChart.setDrawGridBackground(false)
        barChart.setScaleEnabled(false)
        barChart.setBackgroundColor(Color.rgb(255, 255, 255))

    }

    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        lateinit var homeFragment: HomeFragment
    }

}
