package com.example.pruebamosters.data.external

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class ModelResponseApiObject(
    @SerializedName("data") var data: JsonArray? = null
)
