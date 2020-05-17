package com.vakk.common

import org.json.JSONObject


val Any.TAG: String
    get() = javaClass.simpleName

fun Any.toJson(): JSONObject {
    val jsonObject = JSONObject()
    this.javaClass.fields.forEach { field ->
        val value = field.get(this)
        value?.let {
            jsonObject.put(field.name, it)
        } ?: let {
            jsonObject.put(field.name, "null")
        }
    }
    return jsonObject
}

fun Any.toJsonString(): String {
    return toJson().toString()
}

inline fun <reified T>  Array<*>.safeCastFirst(): T? {
    return firstOrNull { it is T }?.safeCast()
}

inline fun <reified T> Any.safeCast(): T? {
    return this as? T?
}

inline fun <reified T> Any.cast(): T {
    return this as T
}
