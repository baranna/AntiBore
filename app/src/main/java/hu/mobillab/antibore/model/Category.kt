package hu.mobillab.antibore.model

import hu.mobillab.antibore.network.dto.OccupationDto

enum class Category(val value: String) {
    EDUCATION("education"),
    RECREATIONAL("recreational"),
    SOCIAL("social"),
    DIY("diy"),
    CHARITY("charity"),
    COOKING("cooking"),
    RELAXATION("relaxation"),
    MUSIC("music"),
    BUSYWORK("busywork");

    companion object {
        fun fromValue(text: String): Category? {
            for (b in Category.values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}