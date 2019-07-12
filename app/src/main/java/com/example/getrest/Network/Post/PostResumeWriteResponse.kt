package com.example.getrest.Network.Post

data class PostResumeWriteResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: PostResumeWriteData?
)

data class PostResumeWriteData(
    val recruitIdx: Int,
    val questionNum: Int,
    val questionContent: String
)