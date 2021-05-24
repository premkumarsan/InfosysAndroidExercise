package com.infosysandroidexercise.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.app.model.RowModel
import com.infosysandroidexercise.app.repository.DashboardRepository
import com.infosysandroidexercise.getOrAwaitValueTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DashboardViewModel? = null

    @Mock
    lateinit var dashboardRepository: DashboardRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DashboardViewModel(dashboardRepository)
    }

    @Test
    fun fetchData() {
        val itemRow = listOf(
            RowModel(
                description = "This is for testing",
                imageHref = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                title = "Android Test"
            ),
            RowModel(
                description = "This is for testing 2",
                imageHref = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                title = "Android Test 2"
            )
        )

        val item = ResponseModel(rows = itemRow, title = "Android Response Test")

        viewModel?.requestDashboardData()

        viewModel?.responseLiveData?.value = item

        val value = viewModel?.responseLiveData?.getOrAwaitValueTest()

        assertThat(value != null).isTrue()

        val responseModel = value as ResponseModel

        assertEquals(responseModel.rows[0].title, "Android Test")
    }

}