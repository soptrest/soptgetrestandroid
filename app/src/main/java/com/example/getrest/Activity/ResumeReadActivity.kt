package com.example.getrest.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.getrest.Fragment.ResumeRead1Fragment
import com.example.getrest.Fragment.ResumeWrite1Fragment
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_resume_read.*
import kotlinx.android.synthetic.main.toolbar_resumeread.*
import kotlinx.android.synthetic.main.toolbar_resumewrite.*
import android.R.id
import android.support.v4.app.Fragment


class ResumeReadActivity : AppCompatActivity() {

//    var resumeIdx: Int = -1

    companion object{
        var resumeIdx: Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_read)

        resumeIdx = intent.getIntExtra("resumeIdx", -1)

//        val bundle = Bundle()
//        bundle.putInt("resumeIdx", resumeIdx)
//        val fm = ResumeRead1Fragment()
//        fm.setArguments(bundle)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_resume_read, ResumeRead1Fragment())
        transaction.commit()

        img_resume_read_back.setOnClickListener {
            finish()
        }
        configureTitleBar()


    }

    private fun configureTitleBar() {
      img_resume_read_back.setOnClickListener {
          finish()
      }
        img_resume_read_edit.setOnClickListener {
            //edit activity로
            val intent: Intent = Intent(this, ResumeEditActivity::class.java)
            startActivity(intent)
        }
    }


}
