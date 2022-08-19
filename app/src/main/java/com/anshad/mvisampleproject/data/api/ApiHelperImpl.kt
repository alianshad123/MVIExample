package com.anshad.mvisampleproject.data.api

import com.anshad.mvisampleproject.data.model.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}