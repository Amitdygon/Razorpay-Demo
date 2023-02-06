package com.okdap.razorpaydemo

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application()  {


    init {
        instance = this
    }



    companion object{
        private var instance: Application? = null
        fun applicationContext(): Context {
            return instance?.applicationContext ?:  instance!!.applicationContext
        }
    }
}