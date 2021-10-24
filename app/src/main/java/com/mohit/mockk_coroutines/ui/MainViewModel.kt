package com.mohit.mockk_coroutines.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohit.mockk_coroutines.data.repository.Repository
import com.mohit.mockk_coroutines.data.domain.GetRepositoriesUseCase
import com.mohit.mockk_coroutines.utils.LiveDataResult
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(mainDispatcher : CoroutineDispatcher, ioDispatcher : CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase) : ViewModel() {

    private val job = SupervisorJob()

    val repositoriesLiveData = MutableLiveData<LiveDataResult<List<Repository>>>()

    private val uiScope = CoroutineScope(mainDispatcher + job)

    val ioScope = CoroutineScope(ioDispatcher + job)

    fun fetchRepositories(user: String) {

        uiScope.launch {
            repositoriesLiveData.value = LiveDataResult.loading()

            try {
                val data = ioScope.async {
                    return@async getRepositoriesUseCase.execute(user)
                }.await()

                repositoriesLiveData.value = LiveDataResult.success(data)
            } catch (e: Exception) {
                repositoriesLiveData.value = LiveDataResult.error(e)
            }


        }

    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }

}