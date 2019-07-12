package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getrest.Activity.WritePortActivity

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio_default.*
import org.jetbrains.anko.support.v4.startActivity

class PortfolioDefaultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio_default, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_portfolio_record.setOnClickListener {
            startActivity<WritePortActivity>()
        }
    }


}
