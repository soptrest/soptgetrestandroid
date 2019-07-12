package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.ChangePwActivity

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.toolbar_mypage.view.*
import org.jetbrains.anko.support.v4.startActivity

class MypageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_mypage, container, false)

        view.img_toolbar_changepw_back.setOnClickListener {
            activity!!.onBackPressed()
        }
        view.img_toolbar_mypage_save.setOnClickListener {
            activity!!.onBackPressed()
        }

        view.img_toolbar_mypage_setup.setOnClickListener {
            addFragment(SettingFragment())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        img_mypage_change_pw.setOnClickListener {
            startActivity<ChangePwActivity>()
        }
    }

    fun addFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.add(R.id.fl_mypage, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = fragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.fl_mypage, fragment)
        transaction.commit()
    }

}
