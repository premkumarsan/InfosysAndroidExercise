package com.infosysandroidexercise.app.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.iconstant.IConstant
import com.infosysandroidexercise.retrofit.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class DashboardRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var compositeDisposable: CompositeDisposable
    private var retrofit: Retrofit? = null

    @Before
    fun setUp() {
        compositeDisposable = CompositeDisposable()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(IConstant.BaseUrl)
            .build()
    }

    @Test
    fun getApiCall() {
        val apiInterface = retrofit?.create(ApiInterface::class.java)

        val observable: Observable<ResponseModel> =
            apiInterface?.dashboardRequest() as Observable<ResponseModel>
        compositeDisposable.add(
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ posts ->
                TestCase.assertEquals("About Canada", posts?.title.toString())
            }, {
            })
        )
    }


    @After
    fun tearDown() {
        compositeDisposable.clear()
    }
}