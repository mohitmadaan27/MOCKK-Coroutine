package com.mohit.mockk_coroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohit.mockk_coroutines.data.domain.GetRepositoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(
                mainDispather,
                ioDispatcher,
                getRepositoriesUseCase
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}