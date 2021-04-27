package hu.mobillab.antibore.repository

import hu.mobillab.antibore.model.Occupation
import javax.inject.Inject

class OccupationRepository @Inject constructor(
    private val occupationDAO: OccupationDAO
){
    suspend fun getAllOccupations() = occupationDAO.getAllOccupations()

    suspend fun getOccupation(key: String) = occupationDAO.getSpecificOccupation(key)

    suspend fun addOccupation(occupation: Occupation) = occupationDAO.insertOccupation(occupation)
}