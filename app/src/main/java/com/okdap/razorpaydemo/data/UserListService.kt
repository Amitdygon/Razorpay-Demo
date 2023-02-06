package com.okdap.razorpaydemo.data

import com.okdap.razorpaydemo.data.model.AssigmentModel
import retrofit2.Response
import retrofit2.http.GET

interface UserListService {

    @GET(ApiEndPoints.ANDROID_ASSIGNMENT)
    suspend fun hitGetAssignment() : Response<AssigmentModel>
}