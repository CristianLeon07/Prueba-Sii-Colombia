package com.example.pruebamosters.data.external

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIConecction {

    public fun getData(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint().URL_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}