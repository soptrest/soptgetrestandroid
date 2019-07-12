package com.example.getrest.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.getrest.Fragment.*

class PortfolioChoicePagerAdapter(fm: FragmentManager, private val num_Fragment: Int): FragmentStatePagerAdapter(fm)
{
    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> PortfolioChoice1Fragment()
            1 -> PortfolioChoice2Fragment()
            2 -> PortfolioChoice3Fragment()
            3 -> PortfolioChoice4Fragment()
            4 -> PortfolioChoice5Fragment()
            5 -> PortfolioChoice6Fragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_Fragment
    }
}