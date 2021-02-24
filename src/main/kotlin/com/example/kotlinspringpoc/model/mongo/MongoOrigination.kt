package com.example.kotlinspringpoc.model.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import kotlin.Double as KotlinDouble

@Document
data class MongoOrigination(
    @Id
    val id: UUID,
    val cpf: String,
    val value: KotlinDouble,
    val originationDate: Date? = Date()
) {
    constructor(cpf: String, value: kotlin.Double): this(
        UUID.randomUUID(),
        cpf,
        value,
    )

}