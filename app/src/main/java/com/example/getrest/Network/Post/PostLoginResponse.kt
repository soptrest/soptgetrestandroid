package com.example.getrest.Network.Post

data class PostLoginResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: PostLoginData?
)

data class PostLoginData(
    val userIdx: Int,
    val userName: String,
    val userToken: String,
    val timestamp: String
)
