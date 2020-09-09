package com.techadhoc.techadhocutils.features.utils.storage

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type

class MySharedPrefDataManager : StorageDataManager {
    override var mySharedPref: MySharedPref
    private var gson: Gson

    constructor(context: Context) {
        mySharedPref = MySharedPref(context)
        gson = Gson()
    }

    override fun <T> put(key: String, value: T) {
        mySharedPref.saveString(key, gson.toJson(value))
    }

    override fun <T> get(key: String, type: Type): T {
        return gson.fromJson(mySharedPref.getStringValue(key), type)
    }

    override fun <T> get(key: String, clas: Class<T>): T {
        return gson.fromJson(mySharedPref.getStringValue(key), clas)
    }

    override fun <T> putList(key: String, list: Array<T>) {
        mySharedPref.saveString(key, gson.toJson(list))
    }

    override fun <T> getList(key: String, clas: Class<Array<T>>): List<T> {
        val list =
            gson.fromJson(mySharedPref.getStringValue(key), clas)
        return if (null == list) {
            ArrayList()
        } else {
            ArrayList(listOf(*list))
        }
    }

    override fun isExist(key: String): Boolean =
        mySharedPref.isExist(key)

    override fun delete(key: String) {
        mySharedPref.deleteValue(key)
    }
}