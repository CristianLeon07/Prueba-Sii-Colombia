package com.example.pruebamosters.data.external

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @POST
    suspend fun postApi(
        @Url url:String,
    ): Response<ModelResponseApiObject>?
}