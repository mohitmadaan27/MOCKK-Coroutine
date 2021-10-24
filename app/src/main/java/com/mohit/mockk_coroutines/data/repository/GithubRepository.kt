package com.mohit.mockk_coroutines.data.repository

interface GithubRepository {
    suspend fun fetchRepositories(username: String) : List<ApiRepository>
}