package com.kriyantechzone.hiltandroidapps.network

import com.kriyantechzone.hiltandroidapps.network.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUserList():List<User>
}