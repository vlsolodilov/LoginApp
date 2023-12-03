package com.solodilov.loginapp.data.repository

import com.solodilov.loginapp.data.datasource.network.EasyPayApi
import com.solodilov.loginapp.data.model.Auth
import com.solodilov.loginapp.data.preferences.Preferences
import com.solodilov.loginapp.domain.entity.Payment
import com.solodilov.loginapp.domain.repository.PaymentRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class PaymentRepositoryImpl@Inject constructor(
    private val api: EasyPayApi,
    private val preferences: Preferences,
) : PaymentRepository {

    override suspend fun login(name: String, password: String): Boolean {
        val authResponse = api.login(Auth(name, password))
        if (authResponse.success == "true") {
            authResponse.tokenResponse?.token?.let { return preferences.setToken(it) }
        }
        return false
    }

    override suspend fun getPaymentList(): List<Payment> {
        val paymentResponse = api.getPayments()
        if (paymentResponse.success == "true") {
            return paymentResponse.paymentResponse?.map { payment ->
                Payment(
                    id = payment.id,
                    title = payment.title,
                    amount = getAmount(payment.amount),
                    created = getDateTime(payment.created),
                )
            } ?: emptyList()
        }
        return emptyList()
    }

    private fun getAmount(value: Any?): Double? =
        when (value) {
            is String -> value.toDoubleOrNull()
            is Number -> value.toDouble()
            else -> null
        }

    private fun getDateTime(value: Int?): LocalDateTime? =
        value?.let { LocalDateTime.ofEpochSecond(it.toLong(), 0, ZoneOffset.UTC) }
}