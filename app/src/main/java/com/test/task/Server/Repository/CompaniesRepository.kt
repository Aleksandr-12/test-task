package com.notepad.Notepad.Retrofit.Repository
import com.notepad.Notepad.Retrofit.Data.Company
import com.notepad.Notepad.Retrofit.RetrofitApi
import com.test.task.Server.Data.CompanyList
import retrofit2.await

class CompaniesRepository(private val retrofitApi: RetrofitApi) {

    suspend fun getCompaniesList(): MutableList<CompanyList> = retrofitApi.getCompaniesList().await()
    suspend fun getCompanyById(id:Int): List<Company> = retrofitApi.getCompanyById(id).await()
}