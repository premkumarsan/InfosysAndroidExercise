package com.infosysandroidexercise.retrofit

import com.infosysandroidexercise.iconstant.IConstant
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitClient {

    @Provides
    @Singleton
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(IConstant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}