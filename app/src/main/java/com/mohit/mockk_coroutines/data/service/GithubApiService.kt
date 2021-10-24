package com.mohit.mockk_coroutines.data.service

import com.mohit.mockk_coroutines.data.repository.ApiRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Call<List<ApiRepository>>

}