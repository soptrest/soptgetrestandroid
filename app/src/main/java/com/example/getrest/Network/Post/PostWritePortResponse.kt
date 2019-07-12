package com.example.getrest.Network.Post

data class PostWritePortResponse (
    val status: Int,
    val success:Boolean,
    val message: String,
    val data: PortfolioData?
)

data class PortfolioData(
    val portfolioIdx: Int
)