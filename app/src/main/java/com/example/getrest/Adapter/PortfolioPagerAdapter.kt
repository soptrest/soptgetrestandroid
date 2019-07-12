package com.example.getrest.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.getrest.Fragment.*

class PortfolioPagerAdapter(fm: FragmentManager, private val num_Fragment: Int): FragmentStatePagerAdapter(fm)
{
    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> Portfolio1Fragment()
            1 -> Portfolio2Fragment()
            2 -> Portfolio3Fragment()
            3 -> Portfolio4Fragment()
            4 -> Portfolio5Fragment()
            5 -> Portfolio6Fragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_Fragment
    }
}