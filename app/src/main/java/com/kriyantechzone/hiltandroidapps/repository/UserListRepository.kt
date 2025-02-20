package com.kriyantechzone.hiltandroidapps.repository

import com.kriyantechzone.hiltandroidapps.network.ApiService
import com.kriyantechzone.hiltandroidapps.network.model.User
import javax.inject.Inject

class UserListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUserList(): List<User> {
        try {
            return apiService.getUserList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}