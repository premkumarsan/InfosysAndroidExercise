package com.infosysandroidexercise.retrofit

import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.iconstant.IConstant
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    @GET(IConstant.dashboardUrl)
    fun dashboardRequest(): Observable<ResponseModel>

}