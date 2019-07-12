package com.example.getrest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.getrest.Activity.WritePortActivity
import com.example.getrest.Activity.WritePortAddCategoryActivity
import com.example.getrest.Adapter.PortfolioPagerAdapter

import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.toolbar_portfolio.*
import org.jetbrains.anko.support.v4.startActivity

class PortfolioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureTitleBar()
        configureMainTab()
    }

    private fun configureTitleBar() {
        btn_toolbar_portfolio_writing.setOnClickListener {
            startActivity<WritePortAddCategoryActivity>()
        }
    }

    private fun configureMainTab() {
        vp_main_portfolio.adapter = PortfolioPagerAdapter(childFragmentManager, 6)
        vp_main_portfolio.offscreenPageLimit = 5
        tl_main_portfolio_category.setupWithViewPager(vp_main_portfolio)

        val navCategoryMainLayout: View = (activity!!.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.tab_layout_portfolio, null, false)

        tl_main_portfolio_category.getTabAt(0)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab1) as RelativeLayout
        tl_main_portfolio_category.getTabAt(1)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab2) as RelativeLayout
        tl_main_portfolio_category.getTabAt(2)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab3) as RelativeLayout
        tl_main_portfolio_category.getTabAt(3)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab4) as RelativeLayout
        tl_main_portfolio_category.getTabAt(4)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab5) as RelativeLayout
        tl_main_portfolio_category.getTabAt(5)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab6) as RelativeLayout
    }

}
