package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.LeaveActivity

import com.example.getrest.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.toolbar_setting.*
import kotlinx.android.synthetic.main.toolbar_setting.view.*
import org.jetbrains.anko.support.v4.startActivity

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_setting, container, false)
        view. btn_toolbar_setting_back.setOnClickListener {
            activity!!.onBackPressed()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txt_setting_leave.setOnClickListener{
            startActivity<LeaveActivity>()
        }
    }


}
