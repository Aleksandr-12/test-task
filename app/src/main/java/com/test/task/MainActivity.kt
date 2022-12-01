package com.test.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notepad.Notepad.Adapters.CompaniesAdapter
import com.notepad.Notepad.Retrofit.*
import com.test.task.Server.Data.CompanyList
import com.test.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerList: RecyclerView
    var companies: MutableList<CompanyList>? = null
    lateinit var adapter: CompaniesAdapter

    private val companiesViewModel: CompaniesViewModel by viewModels {
        CompaniesViewModelFactory()
    }
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerList = binding.recyclerList
        recyclerList.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = layoutManager
        companiesViewModel.companiesList.observe(this, { allTasks ->
            companies = allTasks as MutableList<CompanyList>
            adapter = CompaniesAdapter(this.applicationContext!!, companies!!)
            recyclerList.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        companiesViewModel.getCompaniesList()
    }
}