package com.example.data.network

import com.example.domain.model.User
import retrofit2.Response
import retrofit2.http.GET

internal interface NetworkApi {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}