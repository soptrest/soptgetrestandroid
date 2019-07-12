package com.example.getrest.Network

import com.example.getrest.Network.Delete.DeletePortResponse
import com.example.getrest.Network.Get.*
import com.example.getrest.Network.Post.*
import com.example.getrest.Network.Put.PutModifyPortResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //로그인
    @POST("/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLoginResponse>

    //회원가입
    @POST("/users")
    fun postSignupResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostSignupResponse>

    //포트폴리오 전체 조회
    @GET("/portfolio/portfolio")
    fun getPortfolioListResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetPortfolioListResponse>

    //포트폴리오 상세 조회
    @GET("portfolio/portfolio/{portfolioIdx}")
    fun getViewPortResponse(
        @Header("Content_type") content_type:String,
        @Header("Authorization") authorization : String,
        @Path("portfolioIdx") portfolioIdx : Int
    ):Call<GetViewPortResponse>

    //포트폴리오 작성
    @Multipart
    @POST("portfolio/portfolio")
    fun postWritePortResponse(
        @Header("Authorization") authorization : String,
        @Part("portfolioTitle") portfolioTitle: RequestBody,
        @Part("portfolioBody") portfolioBody: RequestBody,
        @Part("portfolioStartDate") portfolioStartDate:RequestBody,
        @Part("portfolioExpireDate") portfolioExpireDate:RequestBody,
        @Part("portfolioTag") portfolioTag : RequestBody?,
        @Part("portfolioCategory") portfolioCategory:RequestBody,
        @Part portfolioImg: MultipartBody.Part?
    ):Call<PostWritePortResponse>

    //포트폴리오 삭제
    @HTTP(method = "DELETE", path = "/portfolio/portfolio/{portfolioIdx}", hasBody = false)
    fun deletePortResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") Authorization : String,
        @Path("portfolioIdx") portfolioIdx : Int
    ): Call<DeletePortResponse>

    //포트폴리오 수정
    @Multipart
    @PUT("/portfolio/portfolio/{portfolioIdx}")
    fun putModifyPortResponse(
        @Header("Authorization") authorization : String,
        @Path("portfolioIdx") portfolioIdx: Int,
        @Part("portfolioTitle") portfolioTitle: RequestBody,
        @Part("portfolioBody") portfolioBody: RequestBody,
        @Part("portfolioStartDate") portfolioStartDate:RequestBody,
        @Part("portfolioExpireDate") portfolioExpireDate:RequestBody,
        @Part("portfolioTag") portfolioTag : RequestBody?,
        @Part("portfolioCategory") portfolioCategory:RequestBody,
        @Part portfolioImg: MultipartBody.Part?
    ):Call<PutModifyPortResponse>

    //채용공고 전체 조회
    @POST("/recruit")
    fun postRecruitListResponse(
        @Header("Content_type") content_type: String,
        @Header("Authorization") authorization: String,
        @Body() body: JsonObject
    ): Call<PostRecruitListResponse>

    //채용 공고 상세보기
    @GET("/recruit/detail/{recruitIdx}")
    fun getRecruitDetailResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("recruitIdx") recruitIdx: Int
     ): Call<GetRecruitDetailResponse>


    //나의 자소서 문항 불러오기
    @GET("/resume/question/{recruitIdx}")
    fun getResumeQuestionResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("recruitIdx") recruitIdx: Int
    ):Call<GetResumeQuestionResponse>

    //나의 자소서 작성
    @POST("/resume/resume")
    fun postResumeWriteResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Body() body: JsonObject
    ): Call<PostResumeWriteResponse>

    //나의 자소서 전체 조회
    @GET("/resume/resume")
    fun getResumeListResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetResumeListResponse>

    //나의 자소서 상세 조회
    @GET("/resume/resume/{resumeIdx}/{questionNum}")
    fun getResumeDetailResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("resumeIdx") resumeIdx: Int,
        @Path("questionNum") questionNum: Int
    ): Call<GetResumeDetailResponse>

}
