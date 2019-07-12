package com.example.getrest.Network.Get

import com.example.getrest.Data.ResumeDdayListData

data class GetResumeListResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<ResumeDdayListData>
)

