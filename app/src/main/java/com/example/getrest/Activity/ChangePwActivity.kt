package com.example.getrest.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_change_pw.*
import kotlinx.android.synthetic.main.toolbar_change_pw.*
import org.jetbrains.anko.toast

class ChangePwActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)

        btn_change_pw_submit.isEnabled = false

        edt_change_pw_write.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_change_pw_write.text.toString() != "" && edt_change_pw_rewrite.text.toString() != "" && edt_change_pw_write.text.toString() == edt_change_pw_rewrite.text.toString()) {
                    btn_change_pw_submit.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_change_pw_submit.isEnabled = true
                }
                else {
                    btn_change_pw_submit.isEnabled = false
                    btn_change_pw_submit.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
                }
            }
        })

        edt_change_pw_rewrite.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_change_pw_write.text.toString() != "" && edt_change_pw_rewrite.text.toString() != "" && edt_change_pw_write.text.toString() == edt_change_pw_rewrite.text.toString()) {
                    btn_change_pw_submit.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_change_pw_submit.isEnabled = true
                }
                else {
                    btn_change_pw_submit.isEnabled = false
                    btn_change_pw_submit.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
                }
            }
        })

        img_toolbar_changepw_back.setOnClickListener {
            finish()
        }

        btn_change_pw_submit.setOnClickListener {
            toast("비밀번호가 변경되었습니다")
            finish()
        }
      /*  if (signup_u_pw.isNotBlank()) {
            if (signup_u_pw == signup_u_pw_check) {
                btn_change_pw_submit.setBackgroundResource(R.drawable.signup_btn_complete_border)
                btn_change_pw_submit.isEnabled = true
                btn_change_pw_submit.setOnClickListener {
                    finish()
                }
            } else btn_change_pw_submit.isEnabled = false
        }*/
    }
}
