package com.example.getrest.Network.Post

import com.example.getrest.Data.RecruitData

data class PostRecruitListResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<RecruitData>?
)