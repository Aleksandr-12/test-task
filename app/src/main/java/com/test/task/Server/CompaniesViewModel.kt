package com.notepad.Notepad.Retrofit

import androidx.lifecycle.*
import com.notepad.Notepad.Retrofit.Common.Common
import com.notepad.Notepad.Retrofit.Data.Company
import com.notepad.Notepad.Retrofit.Repository.CompaniesRepository
import com.test.task.Server.Data.CompanyList
import kotlinx.coroutines.*

class CompaniesViewModel: ViewModel() {
    private val companiesRepository: CompaniesRepository = CompaniesRepository(Common.retrofitApi)
    var job: Job? = null
    val companiesList = MutableLiveData<List<CompanyList>>()
    fun getCompaniesList() {
        job  = CoroutineScope(Dispatchers.IO).launch {
            val response = companiesRepository.getCompaniesList()
            withContext(Dispatchers.Main) {
                companiesList.postValue(response)
            }
        }
    }
    var job1: Job? = null
    val company = MutableLiveData<List<Company>>()
    fun getCompanyById(id:Int) {
        job1  = CoroutineScope(Dispatchers.IO).launch {
            val response = companiesRepository.getCompanyById(id)
            withContext(Dispatchers.Main) {
                company.postValue(response)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
class CompaniesViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompaniesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompaniesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}