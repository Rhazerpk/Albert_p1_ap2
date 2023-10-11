package com.kotlin.primerparcial_albert.data.repository

import com.kotlin.primerparcial_albert.data.local.dao.DivisionDao
import com.kotlin.primerparcial_albert.data.local.entities.Division
import javax.inject.Inject

class DivisionRepository @Inject constructor(
    private val divisionDao: DivisionDao
) {
    suspend fun save(division: Division) = divisionDao.save(division)

    suspend fun delete(division: Division) = divisionDao.delete(division)

    suspend fun getAll(): List<Division> = divisionDao.getAll()

}