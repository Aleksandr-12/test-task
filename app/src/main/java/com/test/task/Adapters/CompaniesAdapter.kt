package com.notepad.Notepad.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.notepad.Notepad.Retrofit.Common.Common
import com.notepad.Notepad.Retrofit.Data.Company
import com.notepad.Notepad.Retrofit.RetrofitApi
import com.test.task.ActivityCompany
import com.test.task.R
import com.test.task.Server.Data.CompanyList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompaniesAdapter (private val context: Context, private val movieList: MutableList<CompanyList>):
    RecyclerView.Adapter<CompaniesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_name: TextView = itemView.findViewById(R.id.txt_name)
        val txt_img: TextView = itemView.findViewById(R.id.txt_img)
        val company: CardView = itemView.findViewById(R.id.company)
        var mService: RetrofitApi = Common.retrofitApi

        @SuppressLint("ResourceAsColor")
        fun bind(listItem: CompanyList, context: Context) {
            txt_name.text = listItem.name
            txt_img.text = listItem.img
            company.setOnClickListener {
                val intent = Intent(context, ActivityCompany::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("id", listItem.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem,context)
        holder.txt_name.text = movieList[position].name
        holder.txt_img.text = movieList[position].img
    }

}