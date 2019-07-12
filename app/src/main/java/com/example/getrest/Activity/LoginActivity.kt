package com.example.getrest.Activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.getrest.DB.SharedPreferenceController
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.NetworkService
import com.example.getrest.Network.Post.PostLoginResponse
import com.example.getrest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        img_login_auto.setOnClickListener {
            img_login_auto.isSelected = !img_login_auto.isSelected
        }

        btn_login_signin.setOnClickListener {
            val login_u_id: String = edt_login_id.text.toString()
            val login_u_pw: String = edt_login_pw.text.toString()

            postLoginResponse(login_u_id, login_u_pw)
        }

        txt_login_signup.setOnClickListener {
            startActivity<SignupActivity>()
        }

        btn_login_signin.isEnabled = false

        edt_login_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_login_id.text.toString() != "" && edt_login_pw.text.toString() != "") {
                    btn_login_signin.setBackgroundResource(R.drawable.login_button_border_complete)
                    btn_login_signin.setTextColor(Color.parseColor("#ffffff"))
                    btn_login_signin.isEnabled = true
                } else {
                    btn_login_signin.isEnabled = false
                    btn_login_signin.setBackgroundResource(R.drawable.login_button_border)
                    btn_login_signin.setTextColor(Color.parseColor("#82bf59"))
                }
            }
        })

        edt_login_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edt_login_id.text.toString() != "" && edt_login_pw.text.toString() != "") {
                    btn_login_signin.setBackgroundResource(R.drawable.login_button_border_complete)
                    btn_login_signin.setTextColor(Color.parseColor("#ffffff"))
                    btn_login_signin.isEnabled = true
                } else {
                    btn_login_signin.isEnabled = false
                    btn_login_signin.setBackgroundResource(R.drawable.login_button_border)
                    btn_login_signin.setTextColor(Color.parseColor("#82bf59"))
                }
            }
        })

    }
    fun postLoginResponse(u_id: String, u_pw: String){
        var jsonObject = JSONObject()
        jsonObject.put("userEmail", u_id)
        jsonObject.put("userPassword", u_pw)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postLoginResponse: Call<PostLoginResponse> =
                networkService.postLoginResponse("application/json", gsonObject)

        postLoginResponse.enqueue(object : Callback<PostLoginResponse>{
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                toast("Login failed")
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if (response.isSuccessful){
                    if (response.body()!!.status == 200){
                        Log.e("tag", "로그인 성공")
                        val token = response.body()!!.data!!.userToken
                        SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                        toast(SharedPreferenceController.getAuthorizaton(this@LoginActivity))
                        startActivity<MainActivity>()
                    }
                }
            }

        })

    }
}


