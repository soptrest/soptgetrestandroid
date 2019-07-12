package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.ResumeReadActivity
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetResumeDetailResponse
import com.example.getrest.Network.NetworkService

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_resume_read1.*
import kotlinx.android.synthetic.main.fragment_resume_read1.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResumeRead1Fragment : Fragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    val resumeIdx: Int = ResumeReadActivity.resumeIdx

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_resume_read1, container, false)

        view.btn_resumeread_1_right.setOnClickListener{
            replaceFragment(ResumeRead2Fragment())
        }

        view.btn_resumeread_1_right_bottom.setOnClickListener {
            replaceFragment(ResumeRead2Fragment())
        }

//        var resumeIdx: Int = this.arguments!!.getInt("resumeIdx")

//        val resumeIdx = ResumeReadActivity.resumeIdx

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureChangeView()
    }

    private fun configureChangeView(){
        val question_num: Int = 5 //서버에서 줄 문항 갯수

        btn_resumeread_1_left.isEnabled = false
        btn_resumeread_1_left_bottom.isEnabled = false

        if(question_num <= 1) {
            btn_resumeread_1_right.isEnabled = false
            btn_resumeread_1_right_bottom.isEnabled = false
        }

        getResumeDetailResponse()
    }


    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_resume_read, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_resume_read, fragment)
        transaction.commit()
    }

    fun getResumeDetailResponse(){
        val getResumeDetailResponse = networkService.getResumeDetailResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiam" +
                    "VhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3MiOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs", resumeIdx, 1)
        getResumeDetailResponse.enqueue(object : Callback<GetResumeDetailResponse>{
            override fun onFailure(call: Call<GetResumeDetailResponse>, t: Throwable) {
                Log.e("tag", "resume read fail")
            }

            override fun onResponse(call: Call<GetResumeDetailResponse>, response: Response<GetResumeDetailResponse>) {
                if (response.isSuccessful){
                    if (response.body()!!.status == 200){
                        Log.e("tag", "resume read success")


                    }
                }
            }
        })

    }
}
