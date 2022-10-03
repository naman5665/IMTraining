package com.example.project01

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://dummyjson.com/"


interface PersonDataInterface{

    @GET("users")
    fun getPersonsData(@Query("page") page: Int): Call<dataJson>
}


object UserDataRecieve{
    val dataInstance: PersonDataInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dataInstance = retrofit.create(PersonDataInterface::class.java)
    }
}