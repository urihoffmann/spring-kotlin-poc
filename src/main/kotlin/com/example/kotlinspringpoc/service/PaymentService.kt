package com.example.kotlinspringpoc.service

import com.example.kotlinspringpoc.dao.mongo.LimitDAO
import com.example.kotlinspringpoc.dao.mongo.OriginationDAO
import com.example.kotlinspringpoc.dao.mongo.PaymentDAO
import com.example.kotlinspringpoc.dto.PaymentDTO
import com.example.kotlinspringpoc.model.mongo.Limit
import com.example.kotlinspringpoc.model.mongo.Payment
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PaymentService(private val paymentDAO: PaymentDAO, private val originationDAO: OriginationDAO, private val limitDAO: LimitDAO) {

    fun registerPayment(paymentDTO: PaymentDTO) {
        val originationDB = originationDAO.findById(paymentDTO.originationId)
        originationDB.orElseThrow { RuntimeException("There is no Origination with id ${paymentDTO.originationId}. Unable to process Payment ") }

        val originationValue = originationDB.get().value

        if (originationValue != paymentDTO.value) {
            throw RuntimeException(" The payment value does not match with the origination Value!. Unable to process Payment")
        } else {
            val limitDB = limitDAO.findByCpf(paymentDTO.cpf)
            val newLimitValue = limitDB.get().value + paymentDTO.value

            limitDAO.save(Limit(paymentDTO.cpf, newLimitValue))
            paymentDAO.save(Payment(paymentDTO.cpf, paymentDTO.value, paymentDTO.originationId))
        }

    }
}