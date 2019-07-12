package com.example.getrest.Activity

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.getrest.Fragment.ResumeWrite1Fragment
import com.example.getrest.R
import kotlinx.android.synthetic.main.toolbar_resumewrite.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class ResumeWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_write)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_resume_write, ResumeWrite1Fragment())
        transaction.commit()

        img_resume_write_back.setOnClickListener {
            finish()
        }

        configureTitleBar()

    }

    private fun configureTitleBar(){
        img_resume_write_back.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("")
            dialog.setMessage("엇, 그만 쓰려고요?"+"\n"+"지금까지 작성 중이던 내용은 사라져요!")
            dialog.setIcon(R.mipmap.ic_launcher)

            var dialog_listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which){
                        DialogInterface.BUTTON_POSITIVE -> {
                            toast("그만 쓰기")
                            //등록 안함. 서버에는 그전에 임시저장 된데까지만
                            finish()

                        }
                        DialogInterface.BUTTON_NEGATIVE ->
                            toast("취소됨")
                    }
                }
            }
            dialog.setPositiveButton("네",dialog_listener)
            dialog.setNegativeButton("아니요",dialog_listener)

            dialog.show()

        }

        img_resume_write_save.setOnClickListener {
            //임시저장
            Toast.makeText(this, "임시저장 되었습니다", Toast.LENGTH_SHORT).show()
        }

        img_resume_write_done.setOnClickListener {
            //서버로 완전 저장!!
            //인덱스 써서 처음에는 insert, 그 뒤로는 update, 서버에서 다시 주는 값으로 판단

            finish()
        }

    }






}
