package com.example.getrest.Activity

import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import com.bumptech.glide.Glide
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetViewPortResponse
import com.example.getrest.Network.Get.Getviewportdata
import com.example.getrest.Network.NetworkService
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_view_port.*
import kotlinx.android.synthetic.main.toolbar_view_port.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import android.R.id
import com.example.getrest.Network.Delete.DeletePortResponse


class ViewPortActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var portfolioIdx: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_port)

        portfolioIdx = intent.getIntExtra("portfolioIdx", -1)
        if (portfolioIdx == -1) finish()






        img_toolbar_view_port_back.setOnClickListener {
            finish() //포폴메인화면으로
        }

        // 팝업창으로 이미지 갤러리에 저장/삭제
        img_view_photo.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("")
            dialog.setMessage("사진 뭐?")
            dialog.setIcon(R.mipmap.ic_launcher)

            var dialog_listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            toast("갤러리에 저장")
                            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_graphic)
                            saveImage(bitmap)
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            toast("삭제됨")
                            img_view_photo.setImageResource(R.drawable.img_blank_photo)
                        }
                    }
                }
            }
            dialog.setPositiveButton("저장", dialog_listener)
            dialog.setNegativeButton("취소", dialog_listener)
            dialog.show()
        }

        // 수정창이동
        img_toolbar_view_port_modify.setOnClickListener {
            startActivity<ModifyPortAddCategoryActivity>(
                "portfolioIdx" to portfolioIdx)
        }

        // 삭제팝업
        img_toolbar_view_port_delete.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("")
            dialog.setMessage("이 기록을 삭제하시겠어요?")
            dialog.setIcon(R.mipmap.ic_launcher)

            var dialog_listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            toast("기록 삭제")
                            //기록삭제
                            deletePortResponse()
                        }
                        DialogInterface.BUTTON_NEGATIVE ->
                            toast("취소됨")
                    }
                }
            }
            dialog.setPositiveButton("삭제", dialog_listener)
            dialog.setNegativeButton("취소", dialog_listener)
            dialog.show()
        }
        getViewPortResponse()
    }


    private fun getViewPortResponse() {
        val getViewPortResponse = networkService.getViewPortResponse(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbi" +
                    "IsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3MiOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs", portfolioIdx)
        getViewPortResponse.enqueue(object : Callback<GetViewPortResponse> {
            override fun onFailure(call: Call<GetViewPortResponse>, t: Throwable) {
               Log.e("tag","viewport error")
            }

            override fun onResponse(call: Call<GetViewPortResponse>, response: Response<GetViewPortResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e("tag", response.body().toString())
                        Log.e("tag", portfolioIdx.toString())
                        var tmp: ArrayList<Getviewportdata> = response.body()!!.data!!
                        view_port_title.text = tmp[0].portfolioTitle
                        view_start_port.text = tmp[0].portfolioStartDate
                        view_end_port.text = tmp[0].portfolioExpireDate
                        view_port_contents.text = tmp[0].portfolioBody
                        view_port_category.text = tmp[0].portfolioCategory

                        Glide.with(ctx).load(tmp[0].portfolioImg).into(img_view_photo)


//                        var mUri: Uri = Uri.parse(tmp[0].portfolioImg)
//                        var bm = MediaStore.Images.Media.getBitmap(contentResolver, mUri)
//                        img_view_photo.setImageBitmap(bm)
                    }
//
                }
            }
        })

    }

    fun deletePortResponse(){
        val deletePortResponse = networkService.deletePortResponse("application/json","eyJhbGciOiJIUzI1NiIsInR5cC" +
                "I6IkpXVCJ9.eyJ1c2VySWR4IjozMSwidXNlckVtYWlsIjoiMDcxMUBuYXZlci5jb20iLCJpYXQiOjE1NjI4MzA4ODgsImV4cCI6MTU2MjkxNzI4OCwiaXNzIjoic2FuZ3l1bkxFRSJ9.xWjmBLrADRLggowhsa-dvfneuEnGLjdaUTl5bga9TYM", portfolioIdx)
        deletePortResponse.enqueue(object: Callback<DeletePortResponse> {
            override fun onFailure(call: Call<DeletePortResponse>, t: Throwable) {
                Log.e("tag", "실패...")
            }

            override fun onResponse(call: Call<DeletePortResponse>, response: Response<DeletePortResponse>) {
                if(response.isSuccessful){
                    toast(response.body()!!.message)
                    if(response.body()!!.status==200){
                        toast("!!")
                    }
                }
            }
        })
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/getRest"
    }
}