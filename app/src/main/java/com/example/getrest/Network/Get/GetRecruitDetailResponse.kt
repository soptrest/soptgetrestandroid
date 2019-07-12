package com.example.getrest.Network.Get

data class GetRecruitDetailResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<GetRecruitDetailData>?
)

data class GetRecruitDetailData(
    var companyName: String,
    var companyImage: String?,
    var recruitJobCategory: String,
    var recruitExpireDate: String,
    var recruitExperienceLevel: String,
    var recruitRequiredExperienceLevel: String,
    var recruitJobType: String,
    var recruitSalary: String,
    var recruitLocation: String,
    var recruitURL: String
)