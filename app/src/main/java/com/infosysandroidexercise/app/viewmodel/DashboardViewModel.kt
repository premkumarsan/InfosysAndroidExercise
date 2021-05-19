package com.infosysandroidexercise.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.infosysandroidexercise.app.repository.DashboardRepository
import io.reactivex.disposables.CompositeDisposable


class DashboardViewModel(dashboardRepository: DashboardRepository) : ViewModel() {

    private var repository = dashboardRepository
    private var compositeDisposable: CompositeDisposable? = null
    var mutableLiveData: LiveData<Any>
    var progressLiveData: LiveData<Boolean>

    init {
        compositeDisposable = CompositeDisposable()
        mutableLiveData = repository.responseLiveData
        progressLiveData = repository.loadingLiveData
        requestDashboardData()
    }

    private fun requestDashboardData() {
        repository.fetchData(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }
}