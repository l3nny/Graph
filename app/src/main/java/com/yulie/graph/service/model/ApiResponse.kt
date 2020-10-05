package com.yulie.graph.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Data(
    @Expose
    @SerializedName("total_power_list") val total_power_list: ArrayList<String>,
    @Expose
    @SerializedName("speed_list") val speed_list: ArrayList<String>
)

