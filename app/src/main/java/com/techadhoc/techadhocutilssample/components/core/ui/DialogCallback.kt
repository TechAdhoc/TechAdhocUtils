package com.techadhoc.techadhocutilssample.components.core.ui

import android.content.DialogInterface

interface DialogCallback {
    fun doPositive(dialog: DialogInterface, which: Int)
    fun doNegative(dialog: DialogInterface, which: Int)
}