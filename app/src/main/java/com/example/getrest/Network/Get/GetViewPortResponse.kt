package com.example.getrest.Network.Get

data class GetViewPortResponse (
    val status: Int,
    val success:Boolean,
    val message: String,
    val data: ArrayList<Getviewportdata>?
)
data class Getviewportdata(
    var portfolioTitle:String,
    var portfolioStartDate:String,
    var portfolioExpireDate:String,
    var portfolioBody:String,
    var portfolioTag:String?,
    var portfolioCategory: String,
    var portfolioImg: String?
)