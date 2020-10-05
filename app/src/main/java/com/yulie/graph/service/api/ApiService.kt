package com.yulie.graph.service.api


import com.yulie.graph.service.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data")
    fun getRepo(): Call<Data>
}