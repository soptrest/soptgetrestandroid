package com.example.getrest.Data

data class PortfolioData (
    var Authorization: String?,
    var portfolioTitle: String,
    var portfolioBody: String,
    var portfolioImg: String?,
    var portfolioStartDate:String?,
    var portfolioExpireDate: String?,
    var portfolioTag: String?,
    var portfolioCategory: String
)