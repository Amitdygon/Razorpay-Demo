package com.okdap.razorpaydemo.data.viewModel

import com.okdap.razorpaydemo.Application
import com.okdap.razorpaydemo.R


/**
 * This is common validation validation model used to check validation type throught the application
 *
 * @property message error message that is to show
 * @property type identify the type of erro if there are mutiple error meassage on the same screen
 */
data class DataValidation(
    val message: String = Application.applicationContext()?.getString(R.string.something_went_wrong) ?:"",
    var type: Int = 0
)
