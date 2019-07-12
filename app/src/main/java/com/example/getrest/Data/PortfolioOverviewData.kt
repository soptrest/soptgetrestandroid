package com.example.getrest.Data

data class PortfolioOverviewData(
    var portfolioIdx: Int,
    var portfolioTitle: String,
    var portfolioStartDate: String,
    var portfolioExpireDate: String,
    var portfolioCategory: String,
    var portfolioTag: String,
    var portfolioImg: String?
)