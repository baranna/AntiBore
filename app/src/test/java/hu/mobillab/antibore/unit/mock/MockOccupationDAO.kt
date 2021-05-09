package hu.mobillab.antibore.unit.mock

import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.repository.OccupationDAO

open class MockOccupationDAO : OccupationDAO {
    override suspend fun getAllOccupations(): List<Occupation> {
        return occupationList
    }

    override suspend fun getSpecificOccupation(key: String): Occupation? {
        if (key == occupation.key)
            return occupation
        return occupationList.find { it.key == key }
    }

    override suspend fun insertOccupation(occupation: Occupation) {
        occupationList.add(occupation)
    }

    override suspend fun deleteOccupation(occupation: Occupation) {
        occupationList.remove(occupation)
    }

    companion object {
        val occupation = Occupation(
            key = "saved-id",
            "Test saved activity",
            null,
            Category.BUSYWORK,
            null,
            null,
            null
        )
        val occupationList = mutableListOf<Occupation>()
    }

}