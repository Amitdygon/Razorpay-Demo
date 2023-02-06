package com.okdap.razorpaydemo.utils

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun Context.showToastLong(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun TextInputLayout.setError(msg: String?, isError: Boolean) {
    this.error = msg
    this.isErrorEnabled = isError
}