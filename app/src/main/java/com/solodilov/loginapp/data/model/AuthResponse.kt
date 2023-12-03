package com.solodilov.loginapp.data.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("success")
    val success: String?,
    @SerializedName("response")
    val tokenResponse: TokenResponse?,
    @SerializedName("error")
    val easyPayError: EasyPayError?,
)