package com.infosysandroidexercise.app.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.infosysandroidexercise.R
import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.base.APIComponent
import com.infosysandroidexercise.base.MyApplication
import com.infosysandroidexercise.retrofit.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection


class DashboardRepository {

    private var apiInterface: ApiInterface? = null

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var retrofit: Retrofit

    val responseLiveData: MutableLiveData<Any> = MutableLiveData<Any>()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val apiComponent: APIComponent = MyApplication.apiComponent
        apiComponent.inject(this)
        apiInterface = retrofit.create(ApiInterface::class.java)
    }


    /*
     * Function to make Api request through RxJava
     */
    fun fetchData(compositeDisposable: CompositeDisposable?) {
        val observable: Observable<ResponseModel> =
            apiInterface?.dashboardRequest() as Observable<ResponseModel>

        loadingLiveData.postValue(true)
        compositeDisposable?.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ posts ->
                    if (posts != null) {
                        responseLiveData.postValue(posts)
                    } else {
                        responseLiveData.postValue("Response Failed")
                    }
                    loadingLiveData.postValue(false)
                }, {
                    responseLiveData.postValue(handleApiError(it))
                    loadingLiveData.postValue(false)
                })
        )
    }


    /*
    * Function for Error Handling
    */
    private fun handleApiError(error: Throwable): String {
        if (error is ConnectException || error is UnknownHostException) {
            return mContext.resources.getString(R.string.noInternet)
        } else if (error is HttpException) {
            return when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> "Unauthorised User "
                HttpsURLConnection.HTTP_FORBIDDEN -> "Access Denied"
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Internal Server Error"
                HttpsURLConnection.HTTP_BAD_REQUEST -> "Bad Request"
                HttpURLConnection.HTTP_NOT_FOUND -> "Page Not Found"
                HttpURLConnection.HTTP_UNAVAILABLE -> "Service Unavailable"
                else -> error.message()
            }
        }
        return "Unknown Error"
    }
}