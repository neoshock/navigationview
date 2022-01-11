package com.example.navigationview.services

import com.example.navigationview.models.User
import retrofit2.Call
import retrofit2.http.GET

interface Fakeapirest {

    @GET("user")
    fun getUsers(): Call<List<User>>
}