package hu.mobillab.antibore.model

data class Occupation (
        var key: String,
        var activity: String,
        var accessibility: Double,
        var type: Category,
        var participants: Int,
        var price: Double,
        var link: String
)