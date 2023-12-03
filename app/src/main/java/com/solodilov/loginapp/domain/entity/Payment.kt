package com.solodilov.loginapp.domain.entity

import java.time.LocalDateTime

data class Payment(
    val id: Int,
    val title: String,
    val amount: Double?,
    val created: LocalDateTime?,
)
