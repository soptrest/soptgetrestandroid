package com.example.getrest.Network.Get

import com.example.getrest.Data.ResumeQuestionData

data class GetResumeQuestionResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<ResumeQuestionData>?
)
