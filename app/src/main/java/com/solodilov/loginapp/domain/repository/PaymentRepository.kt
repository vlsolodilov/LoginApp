package com.solodilov.loginapp.domain.repository

import com.solodilov.loginapp.domain.entity.Payment

interface PaymentRepository {
    suspend fun login(name: String, password: String): Boolean
    suspend fun getPaymentList(): List<Payment>
}