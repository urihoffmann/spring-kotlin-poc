package com.example.kotlinspringpoc.dto

import java.util.*

data class PaymentDTO(
    val cpf: String,
    val value: Double,
    val originationId: UUID
) {
}