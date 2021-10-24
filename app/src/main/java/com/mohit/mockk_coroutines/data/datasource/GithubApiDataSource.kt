package com.mohit.mockk_coroutines.data.datasource

import com.mohit.mockk_coroutines.data.service.GithubApiService
import com.mohit.mockk_coroutines.data.repository.GithubRepository

class GithubApiDataSource(private val githubApiService: GithubApiService) :
    GithubRepository {

    override suspend fun fetchRepositories(username: String) =
        this.githubApiService.getUserRepositories(username).execute().body() ?: throw NullPointerException("No body")

}