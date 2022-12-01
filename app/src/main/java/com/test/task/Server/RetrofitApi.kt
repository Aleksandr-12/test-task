package com.notepad.Notepad.Retrofit

import com.notepad.Notepad.Retrofit.Data.Company
import com.test.task.Server.Data.CompanyList
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @GET("test.php")
    fun getCompanyById(@Query("id") id: Int):Call<MutableList<Company>>

    @POST("test.php")
    fun getCompaniesList(): Deferred<MutableList<CompanyList>>

}