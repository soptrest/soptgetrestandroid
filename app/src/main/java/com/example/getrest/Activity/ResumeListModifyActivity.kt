package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.getrest.Adapter.ResumeDdayListModifyRecyclerViewAdapter
import com.example.getrest.Data.ResumeDdayListModifyData
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_resume_list_modify.*
import kotlinx.android.synthetic.main.toolbar_resumelist_modify.*

class ResumeListModifyActivity : AppCompatActivity() {

    lateinit var resumeDdayListModifyRecyclerViewAdapter:ResumeDdayListModifyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_list_modify)

        configureTitleBar()
        configureRecyclerView()
    }

    private fun configureTitleBar(){
        img_toolbar_resumelist_modify_done.setOnClickListener {
            finish()
        }
    }

    private fun configureRecyclerView(){
        var dataList: ArrayList<ResumeDdayListModifyData> = ArrayList()
        dataList.add(ResumeDdayListModifyData(
            "D-0", "작성완료", "네이버", "Web 디자이너/모바일.App 디자이너"
        ))
        dataList.add(ResumeDdayListModifyData(
            "D-1", "작성완료", "네이버", "UX Designer"
        ))
        dataList.add(ResumeDdayListModifyData(
            "D-3", "작성중", "오리온", "App 개발자"
        ))
        dataList.add(ResumeDdayListModifyData(
            "D-7", "작성완료", "삼성전자", "SW 개발/R&D"
        ))
        dataList.add(ResumeDdayListModifyData(
            "D-20", "작성중", "KT", "SW Engineer"
        ))

        resumeDdayListModifyRecyclerViewAdapter = ResumeDdayListModifyRecyclerViewAdapter(this, dataList)
        rv_resumedday_list_modify.adapter = resumeDdayListModifyRecyclerViewAdapter
        rv_resumedday_list_modify.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
