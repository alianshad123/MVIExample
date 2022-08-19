package com.anshad.mvisampleproject.data.api

import com.anshad.mvisampleproject.data.model.User

interface ApiHelper {

    suspend fun getUsers(): List<User>

}