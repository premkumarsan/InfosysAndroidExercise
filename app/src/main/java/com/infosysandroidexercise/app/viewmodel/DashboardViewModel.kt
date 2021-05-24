package com.infosysandroidexercise.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.app.repository.DashboardRepository
import io.reactivex.disposables.CompositeDisposable


class DashboardViewModel(dashboardRepository: DashboardRepository) : ViewModel() {

    private var repository = dashboardRepository
    private var compositeDisposable: CompositeDisposable? = null
    var responseLiveData = MutableLiveData<Any>()
    var progressLiveData = MutableLiveData<Boolean>()
    var dataAvailable = MutableLiveData<Boolean>()

    init {
        compositeDisposable = CompositeDisposable()
        dataAvailable.value = true
        requestDashboardData()
    }

    fun requestDashboardData() {
        progressLiveData.value = true
        repository.fetchData(compositeDisposable, dataInterpreter = {
            it?.let {
                responseLiveData.value = it
                dataAvailable.value = if (it is ResponseModel) {
                    it.rows.isNotEmpty()
                } else {
                    false
                }
            }
            progressLiveData.value = false
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }
}