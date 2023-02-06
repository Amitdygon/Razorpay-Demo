package com.okdap.razorpaydemo.data

import androidx.lifecycle.Observer
import com.okdap.razorpaydemo.data.base.ApiError
import com.okdap.razorpaydemo.data.base.ApiResponseData


interface LiveDataObserver<T>: Observer<ApiResponseData> {
    fun onResponseSuccess(statusCode : Int,apiCode : Int,msg : String?)
    fun onException(exception: ApiError, apiCode: Int)
    fun noInternetConnection(apiCode: Int,msg : String?)
    override fun onChanged(apiResponse: ApiResponseData) {
        when(apiResponse){
            is ApiResponseData.API_SUCCEED ->{onResponseSuccess(apiResponse.statusCode,apiResponse.apiCode,apiResponse.msg)}
            is ApiResponseData.API_EXCEPTION ->{onException(apiResponse.exception,apiResponse.apiCode ?: 0)}
            is ApiResponseData.NO_INTERNET ->{noInternetConnection(apiResponse.apiCode,apiResponse.msg)}
        }
    }
}