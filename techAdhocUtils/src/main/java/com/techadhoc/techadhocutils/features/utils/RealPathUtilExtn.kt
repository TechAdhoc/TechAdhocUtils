package com.techadhoc.techadhocutils.features.utils

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Intent?.getFilePath(context: Context): String {
    return this?.data?.let { data -> RealPathUtil.getRealPath(context, data) ?: "" } ?: ""
}

fun Uri?.getFilePath(context: Context): String {
    return this?.let { uri -> RealPathUtil.getRealPath(context, uri) ?: "" } ?: ""
}

fun ClipData.Item?.getFilePath(context: Context): String {
    return this?.uri?.getFilePath(context) ?: ""
}

// Usage
//val selectedPath = intent.getFilePath(context)