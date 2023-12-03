package com.solodilov.loginapp.domain.usecase

import com.solodilov.loginapp.domain.repository.PaymentRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val paymentRepository: PaymentRepository) {

    suspend operator fun invoke(name: String, password: String): Boolean =
        paymentRepository.login(name, password)
}