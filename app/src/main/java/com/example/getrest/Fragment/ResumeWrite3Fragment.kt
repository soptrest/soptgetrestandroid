package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_resume_write3.*
import kotlinx.android.synthetic.main.fragment_resume_write3.view.*


class ResumeWrite3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_resume_write3, container, false)

        view.btn_resumewrite_3_right.setOnClickListener{
            replaceFragment(ResumeWrite4Fragment())
        }

        view.btn_resumewrite_3_right_bottom.setOnClickListener {
            replaceFragment(ResumeWrite4Fragment())
        }

        view.btn_resumewrite_3_left.setOnClickListener {
            replaceFragment(ResumeWrite2Fragment())
        }
        view.btn_resumewrite_3_left_bottom.setOnClickListener {
            replaceFragment(ResumeWrite2Fragment())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureChangeView()
    }

    private fun configureChangeView(){
        val question_num: Int = 5 //서버에서 줄 문항 갯수

        if(question_num <= 3) {
            btn_resumewrite_3_right.isEnabled = false
            btn_resumewrite_3_right_bottom.isEnabled = false
        }
    }


    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_resume_write, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_resume_write, fragment)
        transaction.commit()
    }

}
