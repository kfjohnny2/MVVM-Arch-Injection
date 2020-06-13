package com.johnnylee.mvvm_arch_injection.util.helpers

import android.annotation.SuppressLint
import android.content.Context
import com.johnnylee.mvvm_arch_injection.application.MVVMArchInjectionApplicationManager


private const val PREFS_PRIVATE = "PREFS_PRIVATE"


/**
 *  Method for clearing all preferences
 *  @param context context of the application
 *  */
fun cleanPreferences() {
    val settings = MVVMArchInjectionApplicationManager.appContext!!.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    settings.edit().clear().apply()
}

/**
 *  Method for clearing all preferences
 *  @param context context of the application
 *  */
fun cleanPreferences(context: Context) {
    val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    settings.edit().clear().apply()
}

/**
 *  Method for clearing a specific shared preference
 *  @param key key id name for the identify preference
 *  @param context context of the application
 *  */
fun cleanOnePreference(key: String, context: Context) {
    val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    settings.edit().remove(key).apply()
}

/**
 *  Method for clearing a specific shared preference
 *  @param key key id name for the identify preference
 *  @param context context of the application
 *  */
fun cleanPreferences(keys: Array<String>, context: Context) {
    val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    keys.forEach { settings.edit().remove(it).apply() }

}

/**
 *  Method for setting a specific shared preference
 *  @param obj object for storing in a shared preference
 *  @param key key id name for the identify preference
 *  @param clear clearing preference
 *  @param context context of the application
 *  @return preference in string
 *  */
@SuppressLint("WrongConstant")
fun setPreferences(obj: Any, key: String, clear: Boolean, context: Context) {
    if (clear) {
        val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_ENABLE_WRITE_AHEAD_LOGGING)
        settings.edit().remove(key).apply()
    }

    val sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val prefsPrivateEditor = sharedPreferences.edit()
    prefsPrivateEditor.putString(key, obj.toString() + getPreferencias(key, context))

    prefsPrivateEditor.apply()
}

/**
 *  Method for setting a specific shared preference
 *  @param obj object for storing in a shared preference
 *  @param key key id name for the identify preference
 *  @param clear clearing preference
 *  @param context context of the application
 *  @return preference in string
 *  */
@SuppressLint("WrongConstant")
fun setPreferences(obj: Long, key: String, clear: Boolean, context: Context) {
    if (clear) {
        val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_ENABLE_WRITE_AHEAD_LOGGING)
        settings.edit().remove(key).apply()
    }

    val sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val prefsPrivateEditor = sharedPreferences.edit()
    prefsPrivateEditor.putLong(key, obj)

    prefsPrivateEditor.apply()
}

/**
 *  Method for setting a specific shared preference
 *  @param obj array of objects for storing in a shared preference
 *  @param key key id name for the identify preference
 *  @param clear clearing preference
 *  @param context context of the application
 *  @return preference in string
 *  */
fun setPreferences(obj: Array<Any>, key: String, clear: Boolean, context: Context) {
    if (clear) {
        val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
        settings.edit().remove(key).apply()
    }
    val sb = StringBuilder()
    for (i in obj.indices) {
        sb.append(obj[i]).append(",")
    }
    val sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val prefsPrivateEditor = sharedPreferences.edit()
    prefsPrivateEditor.putString(key, sb.toString() + getPreferencias(key, context))

    prefsPrivateEditor.apply()
}

/**
 *  Access to a specific shared preference
 *  @param key key id name for the identify preference
 *  @param context context of the application
 *  @return preference in string
 *  */
fun getPreferencias(key: String, context: Context): String? {
    var sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val retorno = sharedPreferences.getString(key, "")

    return if (retorno.equals("", ignoreCase = true))
        ""
    else
        retorno
}


/**
 *  Return access token from preferences
 *  @return Access token if it is not null
 * */
fun getAccessToken(): String? {
    val sharedPreferences = MVVMArchInjectionApplicationManager.appContext!!.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val retorno = "Bearer " + sharedPreferences.getString(ACCESS_TOKEN, "")

    return if (retorno.equals("", ignoreCase = true))
        ""
    else
        retorno
}

/**
 *  Return access token from preferences
 *  @return Access token if it is not null
 * */
fun getRefreshToken(): String? {
    val sharedPreferences = MVVMArchInjectionApplicationManager.appContext!!.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val retorno = sharedPreferences.getString(REFRESH_TOKEN, "")

    return if (retorno.equals("", ignoreCase = true))
        ""
    else
        retorno
}

/**
 *  Return access token from preferences
 *  @return Access token if it is not null
 * */
fun getPreferenceLong(key: String): Long {
    val sharedPreferences = MVVMArchInjectionApplicationManager.appContext!!.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    return sharedPreferences.getLong(key, 0L)
}

/**
 *  Shared preference list
 *  @param key key id name for the identify preference array
 *  @param context context of the application
 *  @return list of preferences
 *  */
fun getPreferenciasArray(key: String, context: Context): List<Any> {
    var sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val retorno = sharedPreferences.getString(key, "")!!.split(",")
    return retorno
}