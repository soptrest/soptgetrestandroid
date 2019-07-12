package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.getrest.Adapter.PortfolioChoicePagerAdapter
import com.example.getrest.Adapter.PortfolioPagerAdapter
import com.example.getrest.R
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.toolbar_resume_question_choice.*

class ResumeQuestionChoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_question_choice)

        configureTitleBar()
        configureMainTab()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun configureTitleBar() {
        btn_toolbar_resumequestion_choice_back.setOnClickListener {
            val intent: Intent = Intent(this, ResumeQuestionActivity::class.java)
            startActivity(intent)
        }
        btn_toolbar_resumequestion_choice_done.setOnClickListener {
            val intent: Intent = Intent(this, ResumeQuestionChosenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureMainTab() {
        vp_main_portfolio.adapter = PortfolioChoicePagerAdapter(supportFragmentManager, 6)
        vp_main_portfolio.offscreenPageLimit = 5
        tl_main_portfolio_category.setupWithViewPager(vp_main_portfolio)

        val navCategoryMainLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.tab_layout_portfolio, null, false)

        tl_main_portfolio_category.getTabAt(0)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab1) as RelativeLayout
        tl_main_portfolio_category.getTabAt(1)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab2) as RelativeLayout
        tl_main_portfolio_category.getTabAt(2)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab3) as RelativeLayout
        tl_main_portfolio_category.getTabAt(3)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab4) as RelativeLayout
        tl_main_portfolio_category.getTabAt(4)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab5) as RelativeLayout
        tl_main_portfolio_category.getTabAt(5)!!.customView = navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_portfolio_tab6) as RelativeLayout
    }
}
