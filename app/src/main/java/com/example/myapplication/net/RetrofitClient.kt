package com.example.myapplication.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient{
    private val baseUrl: String = "https://todo.ruangkarya.id"

    fun getClient(): ITodoApi{
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitClient.create(ITodoApi::class.java)
    }
}