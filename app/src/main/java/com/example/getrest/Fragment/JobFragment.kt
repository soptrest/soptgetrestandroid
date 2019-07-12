package com.example.getrest.Fragment


import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.getrest.Adapter.RecruitRecyclerViewAdapter
import com.example.getrest.Data.RecruitData
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Post.PostRecruitListResponse
import com.example.getrest.Network.NetworkService

import com.example.getrest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_recruit_detail.*
import kotlinx.android.synthetic.main.fragment_job.*
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class JobFragment : Fragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    lateinit var recruitRecyclerViewAdapter: RecruitRecyclerViewAdapter

    lateinit var recruit_real_date: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        recruit_date.text =  "${year} . ${month + 1} . ${day}"
        // 채용날짜



        btn_recruit_date.setOnClickListener{
            btn_recruit_date.setImageResource(R.drawable.btn_dropdown)

            var date_listener  = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    recruit_date.text =  "${year} . ${month + 1} . ${dayOfMonth}"
                    Log.e("tag", recruit_date.text.toString())

                    recruit_real_date = "${year}/0${month + 1}/${dayOfMonth}"
//                    var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
//                    recruit_real_date = simpleDateFormat.format(Date())

                    Log.e("tag", recruit_real_date)

                    var dataList: ArrayList<RecruitData> = ArrayList()
                    recruitRecyclerViewAdapter = RecruitRecyclerViewAdapter(context!!, dataList)
                    rv_recruit_list.adapter = recruitRecyclerViewAdapter
                    rv_recruit_list.layoutManager = LinearLayoutManager(context!!)

                    postRecruitListResponse(recruit_real_date)


                }
            }


            var builder = DatePickerDialog(activity!!, date_listener, year, month, day)
            builder.show()
//            builder.setButton(DialogInterface.BUTTON_POSITIVE, "확인",
//                DialogInterface.OnClickListener {dialog, which ->
//                    builder.datePicker.clearFocus()
//
//                    recruit_real_date = "${year}/${month + 1}/${day}"
//                    Log.e("tag", recruit_real_date)
//
//                    var dataList: ArrayList<RecruitData> = ArrayList()
//                    recruitRecyclerViewAdapter = RecruitRecyclerViewAdapter(context!!, dataList)
//                    rv_recruit_list.adapter = recruitRecyclerViewAdapter
//                    rv_recruit_list.layoutManager = LinearLayoutManager(context!!)
//
//                    postRecruitListResponse(recruit_real_date)
//
//
//                })


        }




    }

//    private fun configureRecyclerView() {
//        var dataList: ArrayList<RecruitData> = ArrayList()
//        dataList.add(
//            RecruitData(
//                1, "네이버", "Android 개발", "2019.12"))
//        dataList.add(
//            RecruitData(
//                1, "네이버", "Android 개발", "2019.12"))
//        dataList.add(
//            RecruitData(
//                1, "네이버", "Android 개발", "2019.12"))
//        dataList.add(
//            RecruitData(
//                1, "네이버", "Android 개발", "2019.12"))
//        dataList.add(
//            RecruitData(
//                1, "네이버", "Android 개발", "2019.12"))
//
//        recruitRecyclerViewAdapter = RecruitRecyclerViewAdapter(context!!, dataList)
//        rv_recruit_list.adapter = recruitRecyclerViewAdapter
//        rv_recruit_list.layoutManager = LinearLayoutManager(context!!)
//
//    }

    fun postRecruitListResponse(date: String){
        var jsonObject = JSONObject()
        jsonObject.put("date", date)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postRecruitListResponse: Call<PostRecruitListResponse> =
            networkService.postRecruitListResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3M" +
                        "iOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs",
            gsonObject)
        postRecruitListResponse.enqueue(object: Callback<PostRecruitListResponse> {
            override fun onFailure(call: Call<PostRecruitListResponse>, t: Throwable) {
                Log.e("tag", "채용공고 전체 조회 실패")
            }

            override fun onResponse(call: Call<PostRecruitListResponse>, response: Response<PostRecruitListResponse>) {
                if (response.isSuccessful){ response.body()
                    if (response.body()!!.status == 200){
                        val tmp: ArrayList<RecruitData> = response.body()!!.data!!
                        recruitRecyclerViewAdapter.dataList = tmp
                        recruitRecyclerViewAdapter.notifyDataSetChanged()
                        Log.e("tag", "채용공고 전체 조회 성공")
                    }
                    if (response.body()!!.status == 400){
                        Log.e("tag", "400 실패")
                    }
                    if (response.body()!!.status == 403){
                        Log.e("tag", "403 실패")
                    }
                }
            }
        })
    }

}
