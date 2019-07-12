package com.example.getrest.Network.Put

data class PutModifyPortResponse (
    val status: Int,
    val success:Boolean,
    val message: String,
    val data: ArrayList<PortfolioModifyData>?
)

data class PortfolioModifyData(
    var Authorization: String,
    var portfolioIdx:Int,
    var portfolioTitle: String,
    var portfolioStartDate:String,
    var portfolioExpireDate: String,
    var portfolioTag: String?,
    var portfolioBody: String,
    var portfolioCategory: String,
    var portfolioImg: String?
)