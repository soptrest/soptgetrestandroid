package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.getrest.Adapter.ResumeToTextRecyclerViewAdapter
import com.example.getrest.Data.ResumeToTextData
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_resume_to_text.*
import kotlinx.android.synthetic.main.toolbar_resumequestion.*
import org.jetbrains.anko.startActivity

class ResumeToTextActivity : AppCompatActivity() {

    lateinit var resumeToTextRecyclerViewAdapter: ResumeToTextRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_to_text)

        configureTitleBar()
        configureRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun configureTitleBar() {
        btn_toolbar_resumequestion_back.setOnClickListener {
            finish()
        }
        btn_toolbar_resumequestion_next.setOnClickListener {
            startActivity<ResumeWriteActivity>()
        }

    }

    private fun configureRecyclerView(){
        var dataList: ArrayList<ResumeToTextData> = ArrayList()
        dataList.add(ResumeToTextData("문항1", "내가 생각하는 최고의 서비스디자인은 무엇인가?", "솝트", "리버풀의 이번여름 이적 시장의 정책은 확실하다. 이미 모하메드 살라, 사디오 마네, 호베르투 피르미누, 버질 판 다이크 등 세계 최고의 스쿼드를 갖춘 리버풀이기에 부족한 포지션만 보강을 하고, 남은 자금은 미래를 위해 투자한다는 계획이다.\n" +
                "\n" +
                "그래도 꼭 필요한 영입은 확실하게 마무리 짓겠다는 각오다. 특히 스터리지 등이 팀을 떠나면서 백업 공격수가 부족한 상황. 이에 클롭 감독은 이란 대표팀의 간판 공격수 아즈문의 영입을 노리고 있다.\n" +
                "\n" +
                "이에 대해 러시아 '소브 스포르트'는 \"리버풀의 클롭 감독이 이란의 공격수인 사즈문을 관찰하며 영입을 원하고 있고, 관심을 보이고 있다\"고 보도했다. 이 매체에 따르면 이적료는 1500만 파운드(약 220억 원) 선이 될 것으로 보인다.\n" +
                "\n" +
                "아즈문은 이란 대표팀의 간판스타다. 2011년 이란의 세파한서 프로 무대에 데뷔한 아즈문은 2013년 루빈 카잔을 통해 유럽 무대를 밟았다. 이후 로스토프를 거쳐 2017년 루빈 카잔 유니폼을 입었다. 6년 가까이 러시아 무대에서 활동하며 아즈문은 154경기 42골을 기록했고, 이 사이 이란 대표팀에서 46경기 28골을 기록하며 활약했다.\n" +
                "\n" +
                "아즈문은 아시안컵을 기점으로 이적을 바랐고, 울버햄튼 등과 연결됐지만 결국 그의 행선지는 러시아 명문 제니트가 됐다. 이후 아즈문은 제니트에서도 좋은 활약을 펼치며 수많은 클럽들의 러브콜을 받고 있는 상황이다. "))
        dataList.add(ResumeToTextData("문항2", "내가 생각하는 최고의 서비스기획은 무엇인가?", "씨네필", "리버풀의 이번여름 이적 시장의 정책은 확실하다. 이미 모하메드 살라, 사디오 마네, 호베르투 피르미누, 버질 판 다이크 등 세계 최고의 스쿼드를 갖춘 리버풀이기에 부족한 포지션만 보강을 하고, 남은 자금은 미래를 위해 투자한다는 계획이다.\n" +
                "\n" +
                "그래도 꼭 필요한 영입은 확실하게 마무리 짓겠다는 각오다. 특히 스터리지 등이 팀을 떠나면서 백업 공격수가 부족한 상황. 이에 클롭 감독은 이란 대표팀의 간판 공격수 아즈문의 영입을 노리고 있다.\n" +
                "\n" +
                "이에 대해 러시아 '소브 스포르트'는 \"리버풀의 클롭 감독이 이란의 공격수인 사즈문을 관찰하며 영입을 원하고 있고, 관심을 보이고 있다\"고 보도했다. 이 매체에 따르면 이적료는 1500만 파운드(약 220억 원) 선이 될 것으로 보인다.\n" +
                "\n" +
                "아즈문은 이란 대표팀의 간판스타다. 2011년 이란의 세파한서 프로 무대에 데뷔한 아즈문은 2013년 루빈 카잔을 통해 유럽 무대를 밟았다. 이후 로스토프를 거쳐 2017년 루빈 카잔 유니폼을 입었다. 6년 가까이 러시아 무대에서 활동하며 아즈문은 154경기 42골을 기록했고, 이 사이 이란 대표팀에서 46경기 28골을 기록하며 활약했다.\n" +
                "\n" +
                "아즈문은 아시안컵을 기점으로 이적을 바랐고, 울버햄튼 등과 연결됐지만 결국 그의 행선지는 러시아 명문 제니트가 됐다. 이후 아즈문은 제니트에서도 좋은 활약을 펼치며 수많은 클럽들의 러브콜을 받고 있는 상황이다. "))

        resumeToTextRecyclerViewAdapter = ResumeToTextRecyclerViewAdapter(this, dataList)
        rv_resumequestion_totext.adapter = resumeToTextRecyclerViewAdapter
        rv_resumequestion_totext.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
