package com.yulie.graph.service.api

import com.yulie.graph.service.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRequest {
    fun getList(onResult: (isSuccess: Boolean, response: Data?) -> Unit) {

        RetrofitBuilder.instance.getRepo().enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<Data>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: ApiRequest? = null
        fun getInstance() = INSTANCE
            ?: ApiRequest().also {
                INSTANCE = it
            }
    }
}