package com.anshad.mvisampleproject.data.api

import com.anshad.mvisampleproject.data.model.User
import retrofit2.http.GET

interface ApiService {

   @GET("users")
   suspend fun getUsers(): List<User>
}