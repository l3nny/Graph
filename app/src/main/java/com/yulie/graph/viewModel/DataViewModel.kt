package com.yulie.graph.viewModel

import androidx.lifecycle.MutableLiveData
import com.yulie.graph.service.api.ApiRequest
import com.yulie.graph.service.model.Data

class DataViewModel : BaseViewModel() {
    val listLive = MutableLiveData<Data>()

    fun fetchList() {
        dataLoading.value = true
        ApiRequest.getInstance().getList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                listLive.value = response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

    companion object {
        private var INSTANCE: DataViewModel? = null
        fun getInstance() = INSTANCE
            ?: DataViewModel().also {
                INSTANCE = it
            }
    }
}