package com.example.getrest.Network.Get

data class GetResumeDetailResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<GetResumeDetailData>?
)

data class GetResumeDetailData(
    val questionIdx: Int,
    val questionTitle: String,
    val recruitJobType: String,
    val resumeContent: String
)