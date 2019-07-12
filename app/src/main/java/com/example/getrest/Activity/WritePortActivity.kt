package com.example.getrest.Activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_write_port.*
import kotlinx.android.synthetic.main.toolbar_writeport.*
import org.jetbrains.anko.toast
import java.io.IOException
import java.util.*

class WritePortActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_port)

    }

}