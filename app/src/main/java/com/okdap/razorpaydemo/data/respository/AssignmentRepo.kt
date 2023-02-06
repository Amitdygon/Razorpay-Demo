package com.okdap.razorpaydemo.data.respository

import com.okdap.razorpaydemo.data.UserListService
import com.okdap.razorpaydemo.data.model.AssigmentModel
import retrofit2.Response
import javax.inject.Inject

class AssignmentRepo @Inject constructor(private val apiRepo : UserListService)  {

    suspend fun hitGetAssignment(): Response<AssigmentModel> {
        return apiRepo.hitGetAssignment()
    }
}