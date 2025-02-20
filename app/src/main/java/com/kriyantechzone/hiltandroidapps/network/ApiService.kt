package com.kriyantechzone.hiltandroidapps.network

import com.kriyantechzone.hiltandroidapps.network.model.User
import com.kriyantechzone.hiltandroidapps.network.model.UserDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUserList():List<User>

    @GET("/users/{user}")
    suspend fun getUserDetails(@Path("user") user: String): UserDetails
}