package com.test.task

import android.R.attr.password
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.notepad.Notepad.Retrofit.Common.Common
import com.notepad.Notepad.Retrofit.Common.Common.retrofitApi
import com.notepad.Notepad.Retrofit.CompaniesViewModel
import com.notepad.Notepad.Retrofit.CompaniesViewModelFactory
import com.notepad.Notepad.Retrofit.Data.Company
import com.test.task.databinding.ActivityCompanyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ActivityCompany : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyBinding
    var name: TextView? = null
    var img: TextView? = null
    var desc: TextView? = null
    var phone: TextView? = null
    var www: TextView? = null
    var lat: TextView? = null
    var lon: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar!!.setTitle(resources.getString(R.string.company))
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        name = binding.name
        img = binding.img
        desc = binding.desc
        phone = binding.phone
        www = binding.www
        lat = binding.lat
        lon = binding.lon
        if (getIntent().getIntExtra("id",0)!=0) {
            val id = getIntent().getIntExtra("id",0)
            retrofitApi.getCompanyById(id).enqueue(object : Callback<MutableList<Company>> {
                override fun onResponse(call: Call<MutableList<Company>?>?, response: Response<MutableList<Company>?>) {
                    println("onResponse")
                    System.out.println(response.body().toString())
                    for (cmp in response.body()!!){
                        img!!.text = cmp.name
                        desc!!.text = cmp.description
                        phone!!.text = cmp.phone.toString()
                        www!!.text = cmp.www
                        lat!!.text = cmp.Lat
                        lon!!.text = cmp.Lon
                    }
                }
                override fun onFailure(call: Call<MutableList<Company>?>?, t: Throwable) {
                    println("onFailure")
                    println(t.fillInStackTrace())
                }
            })
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}