package com.example.kotlinspringpoc.model.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.Double as KotlinDouble

@Document
data class Payment(
    @Id
    val id: UUID,
    val cpf: String,
    val value: KotlinDouble,
    val paymentDate: Date,
    val originationId: UUID
) {
    constructor(cpf: String, value: KotlinDouble, originationId: UUID) : this(
        UUID.randomUUID(),
        cpf,
        value,
        Date(),
        originationId
    )
}