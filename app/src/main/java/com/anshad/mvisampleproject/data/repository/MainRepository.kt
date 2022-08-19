package com.anshad.mvisampleproject.data.repository

import com.anshad.mvisampleproject.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}