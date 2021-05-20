package com.infosysandroidexercise.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.infosysandroidexercise.app.repository.DashboardRepository
import io.reactivex.disposables.CompositeDisposable


class DashboardViewModel(dashboardRepository: DashboardRepository) : ViewModel() {

    private var repository = dashboardRepository
    private var compositeDisposable: CompositeDisposable? = null
    var responseLiveData: LiveData<Any>
    var progressLiveData: LiveData<Boolean>

    init {
        compositeDisposable = CompositeDisposable()
        responseLiveData = repository.responseMutableLiveData
        progressLiveData = repository.loadingMutableLiveData
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