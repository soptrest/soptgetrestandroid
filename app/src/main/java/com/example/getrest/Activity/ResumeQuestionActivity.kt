package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.getrest.Adapter.ResumeQuestionRecyclerViewAdapter
import com.example.getrest.Data.ResumeQuestionData
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetPortfolioListResponse
import com.example.getrest.Network.Get.GetResumeQuestionResponse
import com.example.getrest.Network.NetworkService
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_resume_question.*
import kotlinx.android.synthetic.main.toolbar_resumequestion.*
import retrofit2.Call
import retrofit2.Response

class ResumeQuestionActivity : AppCompatActivity() {

    lateinit var resumeQuestionRecyclerViewAdapter: ResumeQuestionRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var recruitIdx: Int = -1
    lateinit var companyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_question)

        recruitIdx = intent.getIntExtra("recruitIdx", -1)
        if (recruitIdx == -1) finish()

        companyName = intent.getStringExtra("companyName")

        configureTitleBar()
//        configureRecyclerView()
        var dataList: ArrayList<ResumeQuestionData> = ArrayList()
        resumeQuestionRecyclerViewAdapter = ResumeQuestionRecyclerViewAdapter(this, dataList)
        rv_resumequestion.adapter = resumeQuestionRecyclerViewAdapter
        rv_resumequestion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getResumeQuestionResponse()

    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun configureTitleBar(){
        btn_toolbar_resumequestion_back.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_toolbar_resumequestion_next.setOnClickListener {
            val intent: Intent = Intent(this, ResumeToTextActivity::class.java)
            startActivity(intent)
        }

        txt_toolbar_resumequestion_company.text= companyName



    }

    private fun configureRecyclerView(){
        var dataList: ArrayList<ResumeQuestionData> = ArrayList()
//        dataList.add(ResumeQuestionData("문항1", "내가 생각하는 최고의 서비스디자인이란 무엇인가?"))
//        dataList.add(ResumeQuestionData("문항2", "다른 사람들과 함께 목표를 달성하기 위해 노력했던 경험은 무엇인지 서술하시오"))
//        dataList.add(ResumeQuestionData("문항3", "배려, 나눔, 협동 중 하나를 골라 관련된 경험을 서술하시오."))
//        dataList.add(ResumeQuestionData("문항4", "학창시절 깊게 읽었던 책에 대해 서술하시오."))
//        dataList.add(ResumeQuestionData("문항5", "학창시절 가장 인상깊었던 동아리에 대해 서술하시오."))

        resumeQuestionRecyclerViewAdapter = ResumeQuestionRecyclerViewAdapter(this, dataList)
        rv_resumequestion.adapter = resumeQuestionRecyclerViewAdapter
        rv_resumequestion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getResumeQuestionResponse(){
        val getResumeQuestionResponse = networkService.getResumeQuestionResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3M" +
                    "iOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs", recruitIdx)
        getResumeQuestionResponse.enqueue(object : retrofit2.Callback<GetResumeQuestionResponse> {
            override fun onFailure(call: Call<GetResumeQuestionResponse>, t: Throwable) {
                Log.e("tag", "문항 보기 실패")
            }

            override fun onResponse(
                call: Call<GetResumeQuestionResponse>,
                response: Response<GetResumeQuestionResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e("tag", "문항 보기 성공")
//                        Log.e("tag", response.body().toString())
                        val tmp: ArrayList<ResumeQuestionData> = response.body()!!.data!!
                        resumeQuestionRecyclerViewAdapter.dataList = tmp
                        resumeQuestionRecyclerViewAdapter.notifyDataSetChanged()

                    }
                }
            }
        })
    }
}



