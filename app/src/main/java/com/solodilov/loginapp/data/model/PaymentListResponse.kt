package com.solodilov.loginapp.data.model


import com.google.gson.annotations.SerializedName

data class PaymentListResponse(
    @SerializedName("success")
    val success: String?,
    @SerializedName("response")
    val paymentResponse: List<PaymentResponse>?
)