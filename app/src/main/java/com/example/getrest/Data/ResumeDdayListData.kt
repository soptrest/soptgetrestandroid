package com.example.getrest.Data

data class ResumeDdayListData(
    var resumeIdx : Int,
    var recruitIdx: Int?,
    var companyIdx: Int?,
    var recruitJobType: String?,
    var recruitStartDate : String,
    var recruitExpireDate : String,
    var leftDate : Int?,
    var companyName: String?,
    var expireCheck: Boolean?
)