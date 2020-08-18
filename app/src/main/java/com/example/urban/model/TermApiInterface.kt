package com.example.urban.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TermApiInterface {
    companion object{
        val instance : TermApiInterface by lazy{
            val retrofit = Retrofit.Builder().baseUrl(
                "https://mashape-community-urban-dictionary.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            retrofit.create(TermApiInterface::class.java)
        }
    }
    @Headers("x-rapidapi-key:dbafd545d9msh86a3e9c13ed9b27p1e4603jsn6f2723f71fd7")
    @GET("define")
    fun getTerm(
        @Query("term") term:String
    ): Single<QueryResponse>
}