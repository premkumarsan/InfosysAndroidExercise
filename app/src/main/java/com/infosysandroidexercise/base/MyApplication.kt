package com.infosysandroidexercise.base

import android.app.Application
import com.infosysandroidexercise.retrofit.RetrofitClient


class MyApplication : Application() {

    companion object {
        lateinit var apiComponent: APIComponent
    }

    override fun onCreate() {
        super.onCreate()
        apiComponent = initDaggerComponent()
    }

    private fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent.builder()
            .retrofitClient(RetrofitClient())
            .appModule(AppModule(this))
            .build()

        return apiComponent
    }
}