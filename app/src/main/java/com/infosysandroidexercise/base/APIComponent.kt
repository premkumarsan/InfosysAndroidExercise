package com.infosysandroidexercise.base

import com.infosysandroidexercise.app.repository.DashboardRepository
import com.infosysandroidexercise.retrofit.RetrofitClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitClient::class])
interface APIComponent {
    fun inject(activity: DashboardRepository)
}