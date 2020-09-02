package com.techadhoc.techadhocutils.features

import android.app.Application
import android.content.Context

 class MyAppUtils : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MyAppUtils
        @JvmStatic
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}