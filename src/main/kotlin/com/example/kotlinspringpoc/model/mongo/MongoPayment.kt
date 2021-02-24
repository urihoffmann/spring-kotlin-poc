package com.example.kotlinspringpoc.model.mongo

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import kotlin.Double as KotlinDouble

@Document
data class MongoPayment(
    @Id
    val id: UUID,
    val cpf: String,
    val value: kotlin.Double,
    val originationId: UUID,
    val paymentDate: Date? = Date(),
) {
    constructor(cpf: String, value: kotlin.Double, originationId: UUID): this(
        UUID.randomUUID(),
        cpf,
        value,
        originationId
    )

}