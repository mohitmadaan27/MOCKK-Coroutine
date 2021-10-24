package com.mohit.mockk_coroutines.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohit.mockk_coroutines.data.domain.GetRepositoriesUseCase
import com.mohit.mockk_coroutines.data.repository.Repository
import com.mohit.mockk_coroutines.utils.LiveDataResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    lateinit var mainViewModel: MainViewModel

    val dispatcher = Dispatchers.Unconfined

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(dispatcher, dispatcher, getRepositoriesUseCase)
    }

    @Test
    fun testFetchRepositories_Positive() {
        coEvery { getRepositoriesUseCase.execute(any()) } returns listOf(
            Repository("a", "name"),
            Repository("b", "name"),
            Repository("c", "name")
        )

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("mohitmadaan27")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.SUCCESS)
    }

    @Test
    fun testFetchRepositories_Negative() {
        coEvery { getRepositoriesUseCase.execute(any()) } coAnswers { throw Exception("No network") }

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("mohitmadaan27")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.ERROR)
    }

}