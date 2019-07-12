package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_resume_edit4.*
import kotlinx.android.synthetic.main.fragment_resume_edit4.view.*


class ResumeEdit4Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_resume_edit4, container, false)

        view.btn_resumeedit_4_right.setOnClickListener{
            replaceFragment(ResumeEdit5Fragment())
        }

        view.btn_resumeedit_4_right_bottom.setOnClickListener {
            replaceFragment(ResumeEdit5Fragment())
        }

        view.btn_resumeedit_4_left.setOnClickListener {
            replaceFragment(ResumeEdit3Fragment())
        }
        view.btn_resumeedit_4_left_bottom.setOnClickListener {
            replaceFragment(ResumeEdit3Fragment())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureChangeView()
    }

    private fun configureChangeView(){
        val question_num: Int = 5 //서버에서 줄 문항 갯수

        if(question_num <= 4) {
            btn_resumeedit_4_right.isEnabled = false
            btn_resumeedit_4_right_bottom.isEnabled = false
        }
    }


    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_resume_edit, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_resume_edit, fragment)
        transaction.commit()
    }

}

