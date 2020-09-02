package com.techadhoc.techadhocutilssample

import android.app.Application
import android.content.Context

 class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MyApp
        @JvmStatic
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}