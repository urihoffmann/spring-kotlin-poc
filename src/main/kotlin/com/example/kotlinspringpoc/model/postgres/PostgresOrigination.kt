package com.example.kotlinspringpoc.model.postgres

import javax.persistence.Id
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "originations")
data class PostgresOrigination(
    @Id
    @Column(name = "id")
    val id: UUID,
    @Column(name = "cpf")
    val cpf: String,
    @Column(name = "value")
    val value: Double,
    @Column(name = "origination_date")
    val originationDate: Date? = Date(),

    @OneToOne(mappedBy = "postgresOrigination")
    val postgresPayment: PostgresPayment?,
) {

}