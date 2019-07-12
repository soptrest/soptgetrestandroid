package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_resume_edit5.*
import kotlinx.android.synthetic.main.fragment_resume_edit5.view.*
import kotlinx.android.synthetic.main.fragment_resume_write5.*
import kotlinx.android.synthetic.main.fragment_resume_write5.view.*


class ResumeEdit5Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_resume_edit5, container, false)

        view.btn_resumeedit_5_left.setOnClickListener {
            replaceFragment(ResumeEdit4Fragment())
        }
        view.btn_resumeedit_5_left_bottom.setOnClickListener {
            replaceFragment(ResumeEdit4Fragment())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureChangeView()
    }

    private fun configureChangeView(){


        btn_resumeedit_5_right.isEnabled = false
        btn_resumeedit_5_right_bottom.isEnabled = false

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
