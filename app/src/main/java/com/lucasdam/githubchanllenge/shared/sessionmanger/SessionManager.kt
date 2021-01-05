package com.lucasdam.githubchanllenge.shared.sessionmanger

import android.content.SharedPreferences
import androidx.multidex.BuildConfig
import com.lucasdam.githubchanllenge.shared.sessionmanger.AppSessionManager.KeyNotFoundException
import com.google.gson.Gson
import kotlin.reflect.KClass

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface SessionManager {
    @Throws(KeyNotFoundException::class)
    fun <T : Any> get(key: String, type: KClass<T>): T
    fun <T> save(key: String, value: T)
    fun remove(key: String)
    fun contains(key: String): Boolean
    fun deleteAll()
}

class AppSessionManager(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : SessionManager {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String, type: KClass<T>) =
        sharedPreferences.let { sharedPreferences ->
            if (!sharedPreferences.contains(key)) throw KeyNotFoundException(key)
            else when (type) {
                String::class -> sharedPreferences.getString(key, null) as T
                Int::class -> sharedPreferences.getInt(key, DEFAULT_INTEGER_VALUE) as T
                Float::class -> sharedPreferences.getFloat(key, DEFAULT_FLOAT_VALUE) as T
                Long::class -> sharedPreferences.getLong(key, DEFAULT_LONG_VALUE) as T
                Boolean::class -> sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VALUE) as T
                else -> gson.fromJson(sharedPreferences.getString(key, null), type.java) as T
            }
        }

    override fun <T> save(key: String, value: T) {
        sharedPreferences.edit().let { editor ->
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Float -> editor.putFloat(key, value)
                is Long -> editor.putLong(key, value)
                is Boolean -> editor.putBoolean(key, value)
                else -> editor.putString(key, gson.toJson(value))
            }.apply()
        }
    }

    override fun remove(key: String) =
        sharedPreferences.edit()
            .remove(key)
            .apply()

    override fun contains(key: String) =
        sharedPreferences.contains(key)

    override fun deleteAll() =
        sharedPreferences.edit()
            .clear()
            .apply()

    class KeyNotFoundException(key: String) : Exception(
        String.format(KEY_NOT_FOUND_EXCEPTION, key)
    )

    companion object {
        const val SHARED_PREFERENCES_NAME = BuildConfig.APPLICATION_ID
        private const val KEY_NOT_FOUND_EXCEPTION = "Key %s does not exist."

        private const val DEFAULT_FLOAT_VALUE = 0F
        private const val DEFAULT_LONG_VALUE = 0L
        private const val DEFAULT_INTEGER_VALUE = 0
        private const val DEFAULT_BOOLEAN_VALUE = false
    }
}
