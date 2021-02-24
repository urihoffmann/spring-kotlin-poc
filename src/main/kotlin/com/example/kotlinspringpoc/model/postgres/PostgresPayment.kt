package com.example.kotlinspringpoc.model.postgres

import javax.persistence.Id
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "payments")
data class PostgresPayment(
    @Id
    @Column(name = "id")
    val id: UUID,
    @Column(name = "cpf")
    val cpf: String,
    @Column(name = "value")
    val value: Double,
    @Column(name = "payment_date")
    val paymentDate: Date = Date(),

    @OneToOne
    @JoinColumn(name = "origination_id")
    val postgresOrigination: PostgresOrigination
) {
}