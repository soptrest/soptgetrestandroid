package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.getrest.Adapter.ResumeQuestionChosenRecyclerViewAdapter
import com.example.getrest.Data.ResumeQuestionChosenData
import com.example.getrest.Data.ResumeQuestionData
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_resume_question_chosen.*
import kotlinx.android.synthetic.main.toolbar_resumequestion.*

class ResumeQuestionChosenActivity : AppCompatActivity() {

    lateinit var resumeQuestionChosenRecyclerViewAdapter: ResumeQuestionChosenRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_question_chosen)

        configureTitleBar()
        configureRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun configureTitleBar() {
        btn_toolbar_resumequestion_next.setOnClickListener {
            val intent: Intent = Intent(this, ResumeToTextActivity::class.java)
            startActivity(intent)
        }
        btn_toolbar_resumequestion_back.setOnClickListener {
            val intent: Intent = Intent(this, ResumeQuestionChoiceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<ResumeQuestionChosenData> = ArrayList()
        dataList.add(ResumeQuestionChosenData("문항1", "내가 생각하는 최고의 서비스디자인이란 무엇인가?"))
        dataList.add(ResumeQuestionChosenData("문항2", "다른 사람들과 함께 목표를 달성하기 위해 노력했던 경험은 무엇인지 서술하시오"))
        dataList.add(ResumeQuestionChosenData("문항3", "배려, 나눔, 협동 중 하나를 골라 관련된 경험을 서술하시오."))
        dataList.add(ResumeQuestionChosenData("문항4", "학창시절 깊게 읽었던 책에 대해 서술하시오."))
        dataList.add(ResumeQuestionChosenData("문항5", "학창시절 가장 인상깊었던 동아리에 대해 서술하시오."))

        resumeQuestionChosenRecyclerViewAdapter = ResumeQuestionChosenRecyclerViewAdapter(this, dataList)
        rv_resume_question_chosen.adapter = resumeQuestionChosenRecyclerViewAdapter
        rv_resume_question_chosen.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
