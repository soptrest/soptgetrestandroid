package com.example.getrest.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.ResumeListModifyActivity
import com.example.getrest.Adapter.ResumeDdayListRecyclerViewAdapter
import com.example.getrest.Data.ResumeDdayListData
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetResumeListResponse
import com.example.getrest.Network.NetworkService

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_resume.*
import kotlinx.android.synthetic.main.toolbar_resume.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResumeFragment : Fragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var resumeDdayListRecyclerViewAdapter: ResumeDdayListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resume, container, false)


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureTitleBar()

        var dataList: ArrayList<ResumeDdayListData> = ArrayList()
        resumeDdayListRecyclerViewAdapter = ResumeDdayListRecyclerViewAdapter(activity!!, dataList)
        rv_resumedday_list.adapter = resumeDdayListRecyclerViewAdapter
        rv_resumedday_list.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)

        getResumeListResponse()
    }


    private fun configureTitleBar(){
        img_toolbar_resumelist_trash.setOnClickListener {
            startActivity<ResumeListModifyActivity>()
        }
    }

//    private fun configureRecyclerView(){
//        var dataList: ArrayList<ResumeDdayListData> = ArrayList()
//        dataList.add(ResumeDdayListData(
//            "D-0", "작성완료", "네이버", "Web 디자이너/모바일.App 디자이너"
//        ))
//        dataList.add(ResumeDdayListData(
//            "D-1", "작성완료", "네이버", "UX Designer"
//        ))
//        dataList.add(
//            ResumeDdayListData(
//            "D-3", "작성중", "오리온", "App 개발자"
//        )
//        )
//        dataList.add(ResumeDdayListData(
//            "D-7", "작성완료", "삼성전자", "SW 개발/R&D"
//        ))
//        dataList.add(ResumeDdayListData(
//            "D-20", "작성중", "KT", "SW Engineer"
//        ))
//
//        resumeDdayListRecyclerViewAdapter = ResumeDdayListRecyclerViewAdapter(activity!!, dataList)
//        rv_resumedday_list.adapter = resumeDdayListRecyclerViewAdapter
//        rv_resumedday_list.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
//    }

    private fun getResumeListResponse(){
        val getResumeListResponse = networkService.getResumeListResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3M" +
                    "iOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs")

        getResumeListResponse.enqueue(object : Callback<GetResumeListResponse>{
            override fun onFailure(call: Call<GetResumeListResponse>, t: Throwable) {
                Log.e("tag", "레주메 응 실패야")
            }

            override fun onResponse(call: Call<GetResumeListResponse>, response: Response<GetResumeListResponse>) {
                if (response.isSuccessful){
                    if (response.body()!!.status == 200){
                        val tmp: ArrayList<ResumeDdayListData> = response.body()!!.data!!
                        resumeDdayListRecyclerViewAdapter.dataList = tmp
                        resumeDdayListRecyclerViewAdapter.notifyDataSetChanged()
                        Log.e("tag", "레주메 리스트 성공")
                    }
                    else if (response.body()!!.status == 400){
                        Log.e("tag", "나의 자소서 전체 조회 선패")
                    }
                    else if (response.body()!!.status == 401){
                        Log.e("tag", "만료된 토큰입니다")
                    }
                }
            }
        })

    }



}
