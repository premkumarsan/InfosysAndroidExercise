package com.infosysandroidexercise.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infosysandroidexercise.app.repository.DashboardRepository


class DashboardViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(DashboardRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}