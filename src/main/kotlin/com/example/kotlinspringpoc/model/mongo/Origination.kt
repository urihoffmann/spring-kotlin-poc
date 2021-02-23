package com.example.kotlinspringpoc.model.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.Double as KotlinDouble

@Document
data class Origination(
    @Id
    val _id: UUID,
    val cpf: String,
    val value: KotlinDouble,
    val originationDate: Date
) {
    constructor(cpf: String, value: KotlinDouble) : this(
        UUID.randomUUID(),
        cpf,
        value,
        Date()
    )
}