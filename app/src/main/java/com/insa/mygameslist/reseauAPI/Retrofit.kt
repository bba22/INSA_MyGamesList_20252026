package com.insa.mygameslist.reseauAPI


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api : IGDBApi = retrofit.create(IGDBApi::class.java)

}