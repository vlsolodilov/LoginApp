package com.solodilov.loginapp.data.model


import com.google.gson.annotations.SerializedName

data class EasyPayError(
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_msg")
    val errorMsg: String?
)