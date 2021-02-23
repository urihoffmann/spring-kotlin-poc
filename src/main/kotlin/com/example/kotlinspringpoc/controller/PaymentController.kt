package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dao.mongo.PaymentDAO
import com.example.kotlinspringpoc.dto.PaymentDTO
import com.example.kotlinspringpoc.model.mongo.Payment
import com.example.kotlinspringpoc.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")

class PaymentController(private val paymentsDAO: PaymentDAO, private val paymentService: PaymentService) {

    @GetMapping
    fun getPayments(): MutableList<Payment> = paymentsDAO.findAll()

    @GetMapping("/{cpf}")
    fun getPaymentsByCpf(@PathVariable cpf: String): Collection<Payment> {
        return paymentsDAO.findAllByCpf(cpf)
    }

    @PostMapping("/register")
    fun registerOrigination(@RequestBody payment: PaymentDTO): ResponseEntity<PaymentDTO> {
        return try {
            paymentService.registerPayment(payment)
            ResponseEntity.ok(payment)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }
    }


}