package com.example.kotlinspringpoc.model.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MongoLimit(
    @Id
    val cpf: String,
    val value: kotlin.Double

) {
}