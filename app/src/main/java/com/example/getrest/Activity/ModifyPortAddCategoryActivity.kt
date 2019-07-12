package com.example.getrest.Activity

import android.app.Activity
import android.app.Activity.*
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.getrest.Network.ApplicationController
import com.example.getrest.Network.Get.GetViewPortResponse
import com.example.getrest.Network.Get.Getviewportdata
import com.example.getrest.Network.NetworkService
import com.example.getrest.Network.Put.PutModifyPortResponse
import com.example.getrest.R
import kotlinx.android.synthetic.main.activity_modify_port.*
import kotlinx.android.synthetic.main.activity_modify_port_add_category.*
import kotlinx.android.synthetic.main.activity_view_port.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.toolbar_modify_port.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.ctx
import org.jetbrains.anko.image
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.*
import java.util.Calendar.*

class ModifyPortAddCategoryActivity : AppCompatActivity() {
    private var dialog: BottomSheetDialog? = null
    private var mImage: MultipartBody.Part? = null

    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    lateinit var selectedPicUri: Uri

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var portfolioIdx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_add_category)

        portfolioIdx = intent.getIntExtra("portfolioIdx", -1)
        if (portfolioIdx == -1) finish()

        showHide(category_modify_port)
        var pic_reinfo: Int = 0;
        var category_reinfo: Int = 0;
        var title_reinfo: Int = 0;
        var start_reinfo: Int = 0;
        var end_reinfo: Int = 0;
        var contents_reinfo: Int = 0;

        img_toolbar_modify_port_back.setOnClickListener {
            // 변경사항 있으면(reinfo중 하나라도 0이 아니면) 팝업창, view port로 이동
            if (pic_reinfo == 0 && category_reinfo == 0 && title_reinfo == 0 && start_reinfo == 0 && end_reinfo == 0 && contents_reinfo == 0)
                finish() // viewport화면
            else {
                var dialog = AlertDialog.Builder(this)
                dialog.setTitle("")
                dialog.setMessage("수정된 데이터가 있는데 뒤로 가십니까")
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
        img_toolbar_modify_port_save.setOnClickListener {
            putModifyPortResponse()
            finish()
        }
        img_modify_photo.setOnClickListener {
            pic_reinfo++
            // 이미지 삭제/변경 팝업, 변경시 갤러리 연결
            img_modify_photo!!.setOnClickListener { showPictureDialog() }
        }

        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        modify_port_category.setOnClickListener {
            Log.d("myTag", "port_category clicked")
            if (dialog == null) {
                dialog = BottomSheetDialog(this)
                dialog!!.setContentView(view)
            }
            dialog!!.show()
        }

        view.cate1.setOnClickListener {
            view.cate1.setBackgroundColor(Color.parseColor("#82bf59"))
            view.cate1_item.setTextColor(Color.parseColor("#ffffff"))
            modify_port_category.text = "대외활동"
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
            modify_port_category.text = "학교활동"
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
            modify_port_category.text = "공모전"
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
            modify_port_category.text = "경력사항"
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
            modify_port_category.text = "기타"
            view.cate1.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate1_item.setTextColor(Color.parseColor("#737373"))
            view.cate2.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate2_item.setTextColor(Color.parseColor("#737373"))
            view.cate3.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate3_item.setTextColor(Color.parseColor("#737373"))
            view.cate4.setBackgroundColor(Color.parseColor("#ffffff"))
            view.cate4_item.setTextColor(Color.parseColor("#737373"))
        }

        modify_port_title.setOnClickListener {
            title_reinfo++
        }
        modify_port_title.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) modify_port_title.setTextColor(Color.parseColor("#82bf59"))
            else modify_port_title.setTextColor(Color.parseColor("#38362e"))
        }
        modify_start_port.setOnClickListener {
            // 포커스시 색 바꿔

            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    modify_start_port.text = "${year} . ${month + 1} . ${dayOfMonth}"
                }
            }
            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()

            start_reinfo++
        }
        modify_start_port.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) modify_start_port.setTextColor(Color.parseColor("#82bf59"))
            else modify_start_port.setTextColor(Color.parseColor("#38362e"))
        }
        modify_end_port.setOnClickListener {
            // 포커스시 색 바꿔

            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    modify_end_port.text = "${year} . ${month + 1} . ${dayOfMonth}"
                }
            }
            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()

            end_reinfo++
        }
        modify_end_port.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) modify_end_port.setTextColor(Color.parseColor("#82bf59"))
            else modify_end_port.setTextColor(Color.parseColor("#38362e"))
        }
        modify_port_contents.setOnClickListener {
            contents_reinfo++
        }
        getViewPortResponse()
    }

    private fun getViewPortResponse() {
        val getViewPortResponse = networkService.getViewPortResponse(
            "application/json",
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsImlhdCI6MTU2MjkxNzg1OSwiZ" +
        "XhwIjoxNTYzMDkwNjU5LCJpc3MiOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs"            , portfolioIdx
        )
        getViewPortResponse.enqueue(object : Callback<GetViewPortResponse> {
            override fun onFailure(call: Call<GetViewPortResponse>, t: Throwable) {
                toast("error")
            }

            override fun onResponse(call: Call<GetViewPortResponse>, response: Response<GetViewPortResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e("tag", portfolioIdx.toString())
                        var tmp: ArrayList<Getviewportdata> = response.body()!!.data!!
                        modify_port_title.setText(tmp[0].portfolioTitle)
                        modify_start_port.text = tmp[0].portfolioStartDate
                        modify_end_port.text = tmp[0].portfolioExpireDate
                        modify_port_contents.setText(tmp[0].portfolioBody)
                        modify_port_category.text = tmp[0].portfolioCategory

                        Glide.with(ctx).load(tmp[0].portfolioImg).into(img_modify_photo)

//                        var mUri: Uri = Uri.parse(tmp[0].portfolioImg)
//                        var bm = MediaStore.Images.Media.getBitmap(contentResolver, mUri)
//                        img_view_photo.setImageBitmap(bm)
                    }
//
                }
            }
        })

    }

    private fun putModifyPortResponse() {

        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo0MywidXNlckVtYWlsIjoiamVhbiIsIm" +
                    "lhdCI6MTU2MjkxNzg1OSwiZXhwIjoxNTYzMDkwNjU5LCJpc3MiOiJzYW5neXVuTEVFIn0.1TryblrHtcc2wCIN9F-ZslsrKiYLe6AWu9-4Icp1TVs"
        val title = modify_port_title.text.toString()
        val contents = modify_port_contents.text.toString()
        val start = modify_start_port.text.toString()
        val end = modify_end_port.text.toString()
        val tag = ""
        val category = modify_port_category.text.toString()

        val title_rb = RequestBody.create(MediaType.parse("text/plain"), title)
        val contents_rb = RequestBody.create(MediaType.parse("text/plain"), contents)
        val start_rb = RequestBody.create(MediaType.parse("text/plain"), start)
        val end_rb = RequestBody.create(MediaType.parse("text/plain"), end)
        var tag_rb: RequestBody? = null
        if (tag != null) tag_rb = RequestBody.create(MediaType.parse("text/plain"), tag)
        val category_rb = RequestBody.create(MediaType.parse("text/plain"), category)
        val putModifyPortResponse = networkService.putModifyPortResponse(
            token,
            portfolioIdx,
            title_rb,
            contents_rb,
            start_rb,
            end_rb,
            tag_rb,
            category_rb,
            mImage
        )

        putModifyPortResponse.enqueue(object : Callback<PutModifyPortResponse> {
            override fun onFailure(call: Call<PutModifyPortResponse>, t: Throwable) {
                toast(title + contents + start + end + tag + category)

            }

            override fun onResponse(call: Call<PutModifyPortResponse>, response: Response<PutModifyPortResponse>) {
                if (response.isSuccessful) {
                    toast(response.body()!!.message)
                    if (response.body()!!.status == 200) {
                        Log.e("tag", portfolioIdx.toString())
                        toast("!!")
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    selectedPicUri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver.openInputStream(selectedPicUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody =
                        RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
                    mImage = MultipartBody.Part.createFormData(
                        "portfolioImg",
                        File(selectedPicUri.toString()).name,
                        photoBody
                    )
                    Glide.with(this).load(selectedPicUri)
                        .thumbnail(0.1f).into(img_modify_photo)
                }
            }
        }
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("")
        pictureDialog.setMessage("사진을 수정할까요")

        var dialog_listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE ->
                        choosePhotoFromGallary()
                    DialogInterface.BUTTON_NEGATIVE ->
                        img_modify_photo.setImageResource(R.drawable.img_port_blank)
                }
            }
        }
        pictureDialog.setPositiveButton("예", dialog_listener)
        pictureDialog.setNegativeButton("삭제하기", dialog_listener)
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    fun Bitmap.rotate(degree: Int): Bitmap {
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
