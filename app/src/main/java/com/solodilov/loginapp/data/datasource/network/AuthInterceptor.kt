package com.solodilov.loginapp.data.datasource.network

import com.solodilov.loginapp.data.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferences: Preferences,
) : Interceptor {

    companion object {
        const val APP_KEY = "12345"
        const val VERSION = "1"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifyRequest = originalRequest.newBuilder().apply {
            addHeader("app-key", APP_KEY)
            addHeader("v", VERSION)
            preferences.getToken()?.let { addHeader("token", it) }
        }.build()
        return chain.proceed(modifyRequest)
    }
}