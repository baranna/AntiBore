package hu.mobillab.antibore.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.mobillab.antibore.model.Occupation

@Database(entities = [Occupation::class], version = 1)
abstract class AntiBoreDatabase : RoomDatabase() {
    abstract fun occupationDao(): OccupationDAO
}