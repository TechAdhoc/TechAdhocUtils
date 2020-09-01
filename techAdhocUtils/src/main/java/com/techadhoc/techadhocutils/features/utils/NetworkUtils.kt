package com.techadhoc.techadhocutils.features.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    private val TYPE_WIFI = 1
    private val TYPE_MOBILE = 2
    private val TYPE_NOT_CONNECTED = 0
     fun getConnectivityStatus(context: Context): Int {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }

    fun getConnectivityStatusString(context: Context): String? {
        val conn = getConnectivityStatus(context)
        var status: String? = null
        if (conn == TYPE_WIFI) {
            status = "Internet working fine"
        } else if (conn == TYPE_MOBILE) {
            status = "Internet working fine"
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Internet connection error"
        }
        return status
    }

    val isConnected: Boolean
        get() {
            val conn = getConnectivityStatus(MyApp.getContext())
            var status = false
            if (conn == TYPE_WIFI) {
                status = true
            } else if (conn == TYPE_MOBILE) {
                status = true
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = false
            }
            return status
        }
}