package hu.mobillab.antibore.mock

import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.repository.OccupationDAO

class MockOccupationDAO : OccupationDAO {
    override fun getAllOccupations(): List<Occupation> {
        TODO("Not yet implemented")
    }

    override fun getSpecificOccupation(key: String): List<Occupation> {
        TODO("Not yet implemented")
    }

    override fun insertOccupation(occupation: Occupation) {
        TODO("Not yet implemented")
    }
}