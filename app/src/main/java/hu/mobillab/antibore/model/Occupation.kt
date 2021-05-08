package hu.mobillab.antibore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "occupation")
data class Occupation (
        @PrimaryKey(autoGenerate = false) var key: String,
        var activity: String,
        var accessibility: Double?,
        var type: Category,
        var participants: Int?,
        var price: Double?,
        var link: String?
)