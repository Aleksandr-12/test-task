package com.notepad.Notepad.Retrofit.Common

import com.notepad.Notepad.Retrofit.RetrofitApi
import com.notepad.Notepad.Retrofit.RetrofitClient

object  Common {
    private const val BASE_URL = "https://lifehack.studio/test_task/"
    val retrofitApi: RetrofitApi
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitApi::class.java)
}