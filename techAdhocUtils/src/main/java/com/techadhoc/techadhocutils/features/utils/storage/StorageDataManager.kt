package com.techadhoc.techadhocutils.features.utils.storage

import java.lang.reflect.Type

interface StorageDataManager {
    val mySharedPref: MySharedPref?
    fun <T> put(key: String, value: T)
    operator fun <T> get(key: String, type: Type): T
    operator fun <T> get(key: String, clas: Class<T>): T

    fun <T> putList(key: String, list: Array<T>)
    fun <T> getList(key: String, clas: Class<Array<T>>): List<T>

    fun isExist(key: String): Boolean
    fun delete(key: String)
}