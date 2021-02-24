package com.example.kotlinspringpoc.model.postgres

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "limits")
data class PostgresLimit(
    @Id
    @Column(name = "cpf")
    val cpf: String,
    @Column(name = "value")
    val value: Double

) {
}