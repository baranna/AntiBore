package hu.mobillab.antibore.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hu.mobillab.antibore.model.Occupation

@Dao
interface OccupationDAO {
    @Query("SELECT * FROM occupation")
    fun getAllOccupations(): List<Occupation>

    @Query("SELECT * FROM occupation WHERE key = :key")
    fun getSpecificOccupation(key: String): List<Occupation>

    @Insert
    fun insertOccupation(occupation: Occupation)
}