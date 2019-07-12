package com.example.getrest.Network.Get

import com.example.getrest.Data.PortfolioOverviewData

data class GetPortfolioListResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<PortfolioOverviewData>?
)