package com.example.getrest.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.NetworkService
import com.example.getrest.Network.Post.PostLoginResponse
import com.example.getrest.Network.Post.PostSignupResponse
import com.example.getrest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.toolbar_signup.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        img_toolbar_signup_back.setOnClickListener {
            finish()
        }

        btn_signup_complete.isEnabled = false

        edt_signup_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_signup_password.text.toString() == edt_signup_password_check.text.toString() && edt_signup_id.text.toString() != "" && edt_signup_name.text.toString() != ""
                    && edt_signup_password.text.toString() != "" && edt_signup_password_check.text.toString() != "") {
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_signup_complete.isEnabled = true
                    btn_signup_complete.setOnClickListener {
                        val signup_u_name: String = edt_signup_name.text.toString()
                        val signup_u_id: String = edt_signup_id.text.toString()
                        val signup_u_pw: String = edt_signup_password.text.toString()

                        postSignupResponse(signup_u_name, signup_u_id, signup_u_pw)
                    }
                }
                else {
                btn_signup_complete.isEnabled = false
                btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
            }

            }
        })

        edt_signup_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_signup_password.text.toString() == edt_signup_password_check.text.toString() && edt_signup_id.text.toString() != "" && edt_signup_name.text.toString() != ""
                    && edt_signup_password.text.toString() != "" && edt_signup_password_check.text.toString() != "") {
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_signup_complete.isEnabled = true
                    btn_signup_complete.setOnClickListener {
                        val signup_u_name: String = edt_signup_name.text.toString()
                        val signup_u_id: String = edt_signup_id.text.toString()
                        val signup_u_pw: String = edt_signup_password.text.toString()

                        postSignupResponse(signup_u_name, signup_u_id, signup_u_pw)
                    }
                }
                else {
                    btn_signup_complete.isEnabled = false
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
                }
            }
        })

        edt_signup_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_signup_password.text.toString() == edt_signup_password_check.text.toString() && edt_signup_id.text.toString() != "" && edt_signup_name.text.toString() != ""
                    && edt_signup_password.text.toString() != "" && edt_signup_password_check.text.toString() != "") {
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_signup_complete.isEnabled = true
                    btn_signup_complete.setOnClickListener {
                        val signup_u_name: String = edt_signup_name.text.toString()
                        val signup_u_id: String = edt_signup_id.text.toString()
                        val signup_u_pw: String = edt_signup_password.text.toString()

                        postSignupResponse(signup_u_name, signup_u_id, signup_u_pw)
                    }
                }
                else {
                    btn_signup_complete.isEnabled = false
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
                }
            }
        })

        edt_signup_password_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_signup_password.text.toString() == edt_signup_password_check.text.toString() && edt_signup_id.text.toString() != "" && edt_signup_name.text.toString() != ""
                    && edt_signup_password.text.toString() != "" && edt_signup_password_check.text.toString() != "") {
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_complete_border)
                    btn_signup_complete.isEnabled = true
                    btn_signup_complete.setOnClickListener {
                        val signup_u_name: String = edt_signup_name.text.toString()
                        val signup_u_id: String = edt_signup_id.text.toString()
                        val signup_u_pw: String = edt_signup_password.text.toString()

                        postSignupResponse(signup_u_name, signup_u_id, signup_u_pw)
                    }
                }
                else {
                    btn_signup_complete.isEnabled = false
                    btn_signup_complete.setBackgroundResource(R.drawable.signup_btn_incomplete_border)
                }
            }
        })
    }


    fun showHide(view: View) {
        view.visibility = View.VISIBLE
    }

    fun postSignupResponse(u_name: String, u_id: String, u_pw: String) {
        var jsonObject = JSONObject()
        jsonObject.put("userName", u_name)
        jsonObject.put("userEmail", u_id)
        jsonObject.put("userPassword", u_pw)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postSignupResponse: Call<PostSignupResponse> =
                networkService.postSignupResponse("application/json", gsonObject)
        postSignupResponse.enqueue(object: Callback<PostSignupResponse>{
            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
               Log.e("tag", "회원가입 실패")
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if (response.isSuccessful){
                    toast(response.body()!!.message)
                    if (response.body()!!.status == 201){
                        showPopup()
                    }
                }
            }
        })
    }

    private fun showPopup() {
        val view = layoutInflater.inflate(R.layout.popup_signup_complete, null)
        val popupWindow =
            PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        val btnOK = view.findViewById(R.id.txt_popup_signup_ok) as TextView

        btnOK.setOnClickListener {
            popupWindow.dismiss()
            finish()
        }
        popupWindow.setOnDismissListener {
            toast("Popup closed")
        }

        TransitionManager.beginDelayedTransition(ll_signup)
        popupWindow.showAtLocation(ll_signup, Gravity.CENTER, 0, 0)

    }


}