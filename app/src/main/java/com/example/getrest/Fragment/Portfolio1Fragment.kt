package com.example.getrest.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.WritePortActivity
import com.example.getrest.Adapter.PortfolioOverviewRecyclerViewAdapter
import com.example.getrest.Data.PortfolioOverviewData
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetPortfolioListResponse
import com.example.getrest.Network.NetworkService

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio1.*
import kotlinx.android.synthetic.main.fragment_portfolio2.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Portfolio1Fragment : Fragment() {

    lateinit var portfolioOverviewRecyclerViewAdapter: PortfolioOverviewRecyclerViewAdapter

//    object Global{
//        internal var pfExist:Boolean = false
//    }

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_portfolio1, container, false)
        return view
//        if (Global.pfExist) {
//            var view: View = inflater.inflate(R.layout.fragment_portfolio1, container, false)
//            return view
//        }
//        else {
//            replaceFragment(PortfolioDefaultFragment())
//        }
//        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<PortfolioOverviewData> = ArrayList()
        portfolioOverviewRecyclerViewAdapter = PortfolioOverviewRecyclerViewAdapter(context!!, dataList)
        rv_portfolio_overview.adapter = portfolioOverviewRecyclerViewAdapter
        rv_portfolio_overview.layoutManager = LinearLayoutManager(context!!)

        getPortfolioListResponse()

    }

    override fun onResume() {
        super.onResume()
        getPortfolioListResponse()
    }

    //게시글 작성 반영
//    override fun onResume() {
//        super.onResume()
//        getPortfolioListResponse()
//    }

//    private fun configureRecyclerView() {
//        var dataList: ArrayList<PortfolioOverviewData> = ArrayList()
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
//
//        portfolioOverviewRecyclerViewAdapter = PortfolioOverviewRecyclerViewAdapter(context!!, dataList)
//        rv_portfolio_overview.adapter = portfolioOverviewRecyclerViewAdapter
//        rv_portfolio_overview.layoutManager = LinearLayoutManager(context!!)
//    }

    private fun getPortfolioListResponse(){
        val getPortfolioListResponse = networkService.getPortfolioListResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3M" +
                    "iOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs")
        getPortfolioListResponse.enqueue(object : retrofit2.Callback<GetPortfolioListResponse>{
            override fun onFailure(call: Call<GetPortfolioListResponse>, t: Throwable) {
                Log.e("tag", "포폴리스트실패")
            }

            override fun onResponse(
                call: Call<GetPortfolioListResponse>,
                response: Response<GetPortfolioListResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        val tmp: ArrayList<PortfolioOverviewData> = response.body()!!.data!!
                        portfolioOverviewRecyclerViewAdapter.dataList = tmp
                        portfolioOverviewRecyclerViewAdapter.notifyDataSetChanged()

                        Log.e("tag", "포폴리스트 성공")
                    }
                    else if (response.body()!!.status == 401){
                        Log.e("tag", "No token")
                    }
                    else if (response.body()!!.status == 404){
                        Log.e("tag", "No user")
                    }
                }
            }
        })

    }

    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_portfolio1, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_portfolio1, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
