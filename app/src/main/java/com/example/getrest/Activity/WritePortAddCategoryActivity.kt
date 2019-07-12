package com.example.getrest.Activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.DatePicker
import com.bumptech.glide.Glide
import com.example.getrest.Fragment.Portfolio1Fragment
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.NetworkService
import com.example.getrest.Network.Post.PostWritePortResponse
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_write_port.*
import kotlinx.android.synthetic.main.activity_write_port_add_category.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.toolbar_writeport.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.util.*

class WritePortAddCategoryActivity : AppCompatActivity() {

    private val GALLERY = 1
    private var dialog: BottomSheetDialog? = null
    private var mImage: MultipartBody.Part? = null

    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    lateinit var selectedPicUri: Uri


    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_port_add_category)


        showHide(category_write_port)

        frame.bringChildToFront(img_write_photo)

        // 입력된 값 색 변하기
        img_toolbar_write_port_back.setOnClickListener {
            if (port_contents.text.toString().isEmpty()) finish() //쓴 내용 없으면 포폴 메인으로
            else {
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("")
                dialog.setMessage("기록 작성을 중단할까요?")
                dialog.setIcon(R.mipmap.ic_launcher)

                var dialog_listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                toast("중단되었습니다")
                                finish()
                            }
                            DialogInterface.BUTTON_NEGATIVE ->
                                toast("취소되었습니다")
                        }
                    }
                }
                dialog.setPositiveButton("예", dialog_listener)
                dialog.setNegativeButton("아니요", dialog_listener)
                dialog.show()
            }
        }

        // 사진 가져오기
        //btn_add_photo!!.setOnClickListener { showPictureDialog() }
        btn_add_photo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }

        // 카테고리
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        port_category.setOnClickListener {
            Log.d("myTag", "port_category clicked")
            port_category.setTextColor(Color.parseColor("#82bf59"))
            if (dialog == null) {
                dialog = BottomSheetDialog(this)
                dialog!!.setContentView(view)
            }
            dialog!!.show()
        }

        view.cate1.setOnClickListener {
            Log.d("myTag", "cate1 clicked")
            view.cate1.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate1_item.setTextColor(Color.parseColor("#ffffff"))
            port_category.setTextColor(Color.parseColor("#38362e"))
            port_category.text="대외활동"
            view.cate2.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate2_item.setTextColor(Color.parseColor("#737373"))
            view.cate3.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate3_item.setTextColor(Color.parseColor("#737373"))
            view.cate4.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate4_item.setTextColor(Color.parseColor("#737373"))
            view.cate5.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate5_item.setTextColor(Color.parseColor("#737373"))
        }
        view.cate2.setOnClickListener {
            view.cate2.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate2_item.setTextColor(Color.parseColor("#ffffff"))
            port_category.setTextColor(Color.parseColor("#38362e"))
            port_category.text="학교활동"
            view.cate1.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate1_item.setTextColor(Color.parseColor("#737373"))
            view.cate3.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate3_item.setTextColor(Color.parseColor("#737373"))
            view.cate4.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate4_item.setTextColor(Color.parseColor("#737373"))
            view.cate5.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate5_item.setTextColor(Color.parseColor("#737373"))
        }
        view.cate3.setOnClickListener {
            view.cate3.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate3_item.setTextColor(Color.parseColor("#ffffff"))
            port_category.setTextColor(Color.parseColor("#38362e"))
            port_category.text="공모전"
            view.cate1.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate1_item.setTextColor(Color.parseColor("#737373"))
            view.cate2.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate2_item.setTextColor(Color.parseColor("#737373"))
            view.cate4.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate4_item.setTextColor(Color.parseColor("#737373"))
            view.cate5.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate5_item.setTextColor(Color.parseColor("#737373"))
        }
        view.cate4.setOnClickListener {
            view.cate4.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate4_item.setTextColor(Color.parseColor("#ffffff"))
            port_category.setTextColor(Color.parseColor("#38362e"))
            port_category.text="경력사항"
            view.cate1.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate1_item.setTextColor(Color.parseColor("#737373"))
            view.cate2.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate2_item.setTextColor(Color.parseColor("#737373"))
            view.cate3.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate3_item.setTextColor(Color.parseColor("#737373"))
            view.cate5.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate5_item.setTextColor(Color.parseColor("#737373"))
        }
        view.cate5.setOnClickListener {
            view.cate5.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate5_item.setTextColor(Color.parseColor("#ffffff"))
            port_category.setTextColor(Color.parseColor("#38362e"))
            port_category.text="기타"
            view.cate1.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate1_item.setTextColor(Color.parseColor("#737373"))
            view.cate2.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate2_item.setTextColor(Color.parseColor("#737373"))
            view.cate3.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate3_item.setTextColor(Color.parseColor("#737373"))
            view.cate4.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate4_item.setTextColor(Color.parseColor("#737373"))
        }


        // 제목수정
        port_title.setOnClickListener {
            port_title.setText("")
            port_title.setTextColor(Color.parseColor("#38362e"))
        }

        // 날짜 수정
        start_port.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    start_port.text = "${year} . ${month + 1} . ${dayOfMonth}"
                    start_port.setTextColor(Color.parseColor("#38362e"))
                }
            }

            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()
        }
        end_port.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    end_port.text = "${year} . ${month + 1} . ${dayOfMonth}"
                    end_port.setTextColor(Color.parseColor("#38362e"))
                }
            }

            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()
        }

        txt_toolbar_write_port_do.setOnClickListener {
            postWritePortResponse()
//            val intent: Intent =  Intent(this, Portfolio1Fragment::class.java)
//            intent.clearTop()
//            startActivity(intent)
            finish()
        }
    }

    fun postWritePortResponse(){

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3M" +
                "iOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs"
        val title = port_title.text.toString()
        val contents = port_contents.text.toString()
        val start = start_port.text.toString()
        val end = end_port.text.toString()
        val tag = ""
        val category = port_category.text.toString()

        val title_rb = RequestBody.create(MediaType.parse("text/plain"),title)
        val contents_rb = RequestBody.create(MediaType.parse("text/plain"),contents)
        val start_rb = RequestBody.create(MediaType.parse("text/plain"),start)
        val end_rb = RequestBody.create(MediaType.parse("text/plain"),end)
        var tag_rb:RequestBody? = null
        if(tag != null) tag_rb = RequestBody.create(MediaType.parse("text/plain"),tag)
        val category_rb = RequestBody.create(MediaType.parse("text/plain"),category)
        Log.d("~~~~~~~: ","ff")

//       val options = BitmapFactory.Options()
//        val inputStream: InputStream = contentResolver.openInputStream(selectedPicUri)
//        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
//        val photoBody = RequestBody.create(MediaType.parse("image/jpg"),byteArrayOutputStream.toByteArray())
//        val photo_rb = MultipartBody.Part.createFormData("portfolioImg",File(selectedPicUri.toString()).name, photoBody)
        //Log.d("portfolio",portfolioImg.toString())
        val postWritePortResponse = networkService.postWritePortResponse(token,title_rb,contents_rb, start_rb, end_rb, tag_rb, category_rb, mImage)

        /* var jsonObject = JSONObject()
         jsonObject.put("portfolioTitle",title)
         jsonObject.put("portfolioBody",contents)
         jsonObject.put("portfolioImg",img)
         jsonObject.put("portfolioStartDate",start)
         jsonObject.put("portfolioExpireDate",end)
         jsonObject.put("portfolioTag",tag)
         jsonObject.put("portfolioCategory",category)

         val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
         val postWritePortResponse: Call<PostWritePortResponse> =
                 networkService.postWritePortResponse("multipart/form-data",
                         "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoyNywidXNlckVtYWlsIjoiZW1haWwzIiwiaWF0IjoxNTYyNzQzMjMxLCJleHAiOjE1NjI4Mjk2MzEsImlzcyI6InNhbmd5dW5MRUUifQ.-6CbfDUOAWPb1VcCdKBOlWiiOD7_iUBxL_UH6HthR5s"
                                 ,)*/

        postWritePortResponse.enqueue(object: Callback<PostWritePortResponse> {
            override fun onFailure(call: Call<PostWritePortResponse>, t: Throwable) {
                toast("!!")
            }

            override fun onResponse(call: Call<PostWritePortResponse>, response: Response<PostWritePortResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        toast(response.body()!!.message)

                    }
                }
            }
        })

    }


    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }/*
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("")
        pictureDialog.setMessage("사진을 불러올까요")

        var dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        choosePhotoFromGallary()
                    DialogInterface.BUTTON_NEGATIVE ->
                        toast("취소되었습니다")
                }
            }
        }
        pictureDialog.setPositiveButton("예",dialog_listener)
        pictureDialog.setNegativeButton("아니요",dialog_listener)
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                data?.let{
                    selectedPicUri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver.openInputStream(selectedPicUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"),byteArrayOutputStream.toByteArray())
                    mImage = MultipartBody.Part.createFormData("portfolioImg",
                        File(selectedPicUri.toString()).name, photoBody)
                    Glide.with(this).load(selectedPicUri)
                        .thumbnail(0.1f).into(img_write_photo)
                }
            }
        }
    }


    /*
        public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

            super.onActivityResult(requestCode, resultCode, data)
            *//* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*//*
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val rotatedBitmap = bitmap.rotate(90)
                    val path = saveImage(bitmap)
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
                    // val resizedBitmap = resizeBitmap(bitmap,img_write_photo.width,img_write_photo.height)
                    img_write_photo!!.setImageBitmap(rotatedBitmap)
                    default_img.setImageBitmap(null)
                    frame.bringChildToFront(img_write_photo)
                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        *//*else if (requestCode == CAMERA)
        {
            val companyImage = data!!.extras!!.get("data") as Bitmap
            img_write_photo!!.setImageBitmap(companyImage)
            saveImage(companyImage)
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
        }*//*
    }
    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }
    companion object {
        private val IMAGE_DIRECTORY = "/getRest"
    }*/
    fun Bitmap.rotate(degree:Int):Bitmap{
        // Initialize a new matrix
        val matrix = Matrix()

        // Rotate the bitmap
        matrix.postRotate(degree.toFloat())

        // Resize the bitmap
        val scaledBitmap = Bitmap.createScaledBitmap(
            this,
            width,
            height,
            true
        )

        // Create and return the rotated bitmap
        return Bitmap.createBitmap(
            scaledBitmap,
            0,
            0,
            scaledBitmap.width,
            scaledBitmap.height,
            matrix,
            true
        )
    }

}

