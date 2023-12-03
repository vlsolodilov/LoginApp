package com.solodilov.loginapp.data.preferences

interface Preferences {

    fun getToken(): String?
    fun setToken(token: String): Boolean
}