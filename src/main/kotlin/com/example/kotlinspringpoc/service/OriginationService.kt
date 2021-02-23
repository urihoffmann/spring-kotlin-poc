package com.example.kotlinspringpoc.service

import com.example.kotlinspringpoc.dao.mongo.LimitDAO
import com.example.kotlinspringpoc.dao.mongo.OriginationDAO
import com.example.kotlinspringpoc.dto.OriginationDTO
import com.example.kotlinspringpoc.model.mongo.Limit
import com.example.kotlinspringpoc.model.mongo.Origination
import org.springframework.stereotype.Service

@Service
class OriginationService(private val originationDAO: OriginationDAO, private val limitDAO: LimitDAO) {

    fun registerOrigination(originationDTO: OriginationDTO) {
        val limitDB = limitDAO.findByCpf(originationDTO.cpf)
        limitDB.orElseThrow { RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have limit") }

        val newLimitValue = limitDB.get().value - originationDTO.value

        if (newLimitValue < 0) {
            throw RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have sufficient limit")
        } else {
            limitDAO.save(Limit(originationDTO.cpf, newLimitValue))
            originationDAO.save(Origination(originationDTO.cpf, originationDTO.value))
        }
    }
}