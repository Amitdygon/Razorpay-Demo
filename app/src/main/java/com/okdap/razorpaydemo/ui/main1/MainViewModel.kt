package com.okdap.razorpaydemo.ui.main1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okdap.razorpaydemo.R
import com.okdap.razorpaydemo.data.model.AssigmentModel
import com.okdap.razorpaydemo.data.model.EnteredModel
import com.okdap.razorpaydemo.data.respository.AssignmentRepo
import com.okdap.razorpaydemo.data.viewModel.CoroutinesBase
import com.okdap.razorpaydemo.data.viewModel.DataValidation
import com.okdap.razorpaydemo.utils.AppUtils
import com.okdap.razorpaydemo.utils.ValidationCode
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val assignmentRepo: AssignmentRepo
) : ViewModel() {
    private val mValidationLiveData by lazy { MutableLiveData<DataValidation>() }

    val name = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val city = MutableLiveData<String>()

    private val _assignmentModelResponse = MutableLiveData<AssigmentModel>()
    val assignmentModelResponse: LiveData<AssigmentModel> = _assignmentModelResponse

    private val _enterResponse = MutableLiveData<EnteredModel?>()
    val enterResponse: LiveData<EnteredModel?> = _enterResponse


    fun validation() {
        if (name.value.isNullOrEmpty()) {
            mValidationLiveData.value =
                DataValidation(context.getString(R.string.name_validation), ValidationCode.NAME)
        } else if (phoneNumber.value.isNullOrEmpty()) {
            mValidationLiveData.value = DataValidation(
                context.getString(R.string.phone_validation),
                ValidationCode.PHONE_NUMBER
            )
        } else if (city.value.isNullOrEmpty()) {
            mValidationLiveData.value =
                DataValidation(context.getString(R.string.city_validation), ValidationCode.CITY)
        } else if (!AppUtils.isPhoneNumberValid(phoneNumber.value ?: "")) {
            mValidationLiveData.value = DataValidation(
                context.getString(R.string.invalid_phone_number),
                ValidationCode.PHONE_NUMBER
            )
        } else {
            gotoNextScreen()
        }

    }

    private fun gotoNextScreen() {
        _enterResponse.value = EnteredModel(name.value, phoneNumber.value, city.value)
    }

    init {
        hitApi()
    }

    private fun hitApi() {
        CoroutinesBase.main {
            if (AppUtils.isInternetAvailable()) {
                val response = assignmentRepo.hitGetAssignment()
                if (response.isSuccessful) {
                    _assignmentModelResponse.value = response.body()
                }
            }
        }
    }

    fun getErrorValidation() = mValidationLiveData

    fun clearResponse(){
        _enterResponse.value = null
    }


}