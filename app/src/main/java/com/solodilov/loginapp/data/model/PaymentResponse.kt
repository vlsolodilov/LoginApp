package com.solodilov.loginapp.data.model


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("amount")
    val amount: Any?,
    @SerializedName("created")
    val created: Int?
)