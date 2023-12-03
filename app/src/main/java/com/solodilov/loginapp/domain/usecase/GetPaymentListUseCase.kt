package com.solodilov.loginapp.domain.usecase

import com.solodilov.loginapp.domain.entity.Payment
import com.solodilov.loginapp.domain.repository.PaymentRepository
import javax.inject.Inject

class GetPaymentListUseCase @Inject constructor(private val paymentRepository: PaymentRepository) {

    suspend operator fun invoke(): List<Payment> = paymentRepository.getPaymentList()
}