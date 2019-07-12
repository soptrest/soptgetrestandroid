package com.example.getrest.Activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetRecruitDetailData
import com.example.getrest.Network.Get.GetRecruitDetailResponse
import com.example.getrest.Network.NetworkService
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_recruit_detail.*
import kotlinx.android.synthetic.main.toolbar_recruit_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RecruitDetailActivity : AppCompatActivity() {

    var recruitIdx: Int = -1
    lateinit var companyName: String
//    lateinit var recruitURL: String

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recruit_detail)

        recruitIdx = intent.getIntExtra("recruitIdx", -1)
        if (recruitIdx == -1) finish()

        companyName = intent.getStringExtra("companyName")

        getRecruitDetailResponse()

        btn_toolbar_recruit_back.setOnClickListener {
            finish()
        }

//        recruit_url.setOnClickListener{
//            //서버에서 url받아서 이동
//            val uri = Uri.parse(recruitURL)
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }

        recruit_submit.setOnClickListener {
            startActivity<ResumeQuestionActivity>("recruitIdx" to recruitIdx, "companyName" to companyName)
        }
    }

    private fun getRecruitDetailResponse(){
        val getRecruitDetailResponse = networkService.getRecruitDetailResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbi" +
                    "IsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3MiOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs", recruitIdx)
        getRecruitDetailResponse.enqueue(object : Callback<GetRecruitDetailResponse>{
            override fun onFailure(call: Call<GetRecruitDetailResponse>, t: Throwable) {
                Log.e("tag", "채용공고 상세보기 실패")
//                Log.e("tag", t.toString())
            }

            override fun onResponse(
                call: Call<GetRecruitDetailResponse>,
                response: Response<GetRecruitDetailResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("tag", response.body().toString())
                    if (response.body()!!.status == 200) {
//                        Log.e("tag", response.body().toString())
                        Log.e("tag", "채용공고 상세보기 성공")
                        var tmp: ArrayList<GetRecruitDetailData> = response.body()!!.data!!
                        txt_rv_item_recruit_overview_company.text = tmp[0].companyName
                        txt_rv_item_recruit_overview_task.text = tmp[0].recruitJobCategory
                        txt_rv_item_recruit_overview_deadline.text = tmp[0].recruitExpireDate
                        txt_rv_item_recruit_overview_experienceLevel.text = tmp[0].recruitExperienceLevel
                        txt_rv_item_recruit_overview_required_experienceLevel.text = tmp[0].recruitRequiredExperienceLevel
                        txt_rv_item_recruit_overview_salary.text = tmp[0].recruitSalary
                        txt_rv_item_recruit_overview_jobtype.text = tmp[0].recruitJobType
                        txt_rv_item_recruit_overview_location.text = tmp[0].recruitLocation
                        txt_rv_item_recruit_overview_url.text = tmp[0].recruitURL

                        Glide.with(ctx).load(tmp[0].companyImage).into(img_rv_item_recruit_overview_thumbnail)

//                        recruitURL = tmp[0].recruitURL


                    }
                }
            }
        })

    }

}
