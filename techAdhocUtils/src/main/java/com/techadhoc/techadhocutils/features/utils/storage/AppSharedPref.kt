package com.techadhoc.techadhocutils.features.utils.storage

import android.content.Context

/**
 * @author: Prabal
 * Secure Application Shared Preference
 * */
class AppSharedPref {
    private var storageDataManager: StorageDataManager
    var context: Context

    constructor(context: Context) {
        this.context = context
        this.storageDataManager = MySharedPrefDataManager(context)
    }

    // implement app usage method ... over business layer
    fun saveData(key: String, name: String) {
        storageDataManager.put(key, name)
    }
}