package hu.mobillab.antibore.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hu.mobillab.antibore.model.Occupation

@Dao
interface OccupationDAO {
    @Query("SELECT * FROM occupation")
    suspend fun getAllOccupations(): List<Occupation>

    @Query("SELECT * FROM occupation WHERE key = :key LIMIT 1")
    suspend fun getSpecificOccupation(key: String): Occupation?

    @Insert
    suspend fun insertOccupation(occupation: Occupation)
}