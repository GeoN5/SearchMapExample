package com.example.geonho.taehwanstudy.util

import android.content.Context
import android.content.SharedPreferences


fun Context.getData(key : String) : String {
    val sharedPreferences : SharedPreferences = getSharedPreferences("test",Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}

fun Context.saveData(key : String, value : String) {
    val sharedPreferences : SharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE)
    val editor : SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString(key, value).apply()
}

fun List<String>.saveList(context: Context,key: String){
    val sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putStringSet(key, this.toMutableSet()).apply()
}

fun Context.getList(key: String):List<String>{
    val sharedPreferences = getSharedPreferences("test",Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet(key, mutableSetOf()).toList()
}