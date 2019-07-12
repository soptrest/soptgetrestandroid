package com.example.getrest.Activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_modify_port.*
import kotlinx.android.synthetic.main.toolbar_modify_port.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class ModifyPortActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port)

    }
}