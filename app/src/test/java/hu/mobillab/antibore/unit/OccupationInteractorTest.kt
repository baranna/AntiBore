package hu.mobillab.antibore.unit

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.repository.OccupationRepository
import hu.mobillab.antibore.unit.mock.MockOccupationApi
import hu.mobillab.antibore.unit.mock.MockOccupationDAO
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class OccupationInteractorTest {

    private lateinit var interactor: OccupationInteractor

    private lateinit var api: OccupationApi

    private lateinit var repository: OccupationRepository

    private lateinit var testOccupation: Occupation

    @Before
    fun setup() {
        api = spyk(MockOccupationApi())
        repository = spyk(OccupationRepository(spyk(MockOccupationDAO())))
        interactor =
            OccupationInteractor(api, repository)
        testOccupation =
            Occupation(key = "test-id", "test", null, Category.BUSYWORK, null, null, null)
        MockOccupationDAO.occupationList.clear()
    }

    @Test
    fun `should return 10 random occupations from api`() {
        val occupations = runBlocking {
            interactor.getOccupations()
        }

        coVerify(exactly = 10) { api.getActivity(allAny()) }
        assert(occupations.size == 10)
    }

    @Test
    fun `should return occupation from api if not saved`() {
        val fromDb = runBlocking {
            val (_, fromDb) = interactor.getOccupation("not-saved-id")
            fromDb
        }

        coVerify(exactly = 1) { repository.getOccupation("not-saved-id") }
        coVerify(exactly = 1) { api.getActivity("not-saved-id") }
        assert(!fromDb)
    }

    @Test
    fun `should return occupation from db if saved`() {
        val fromDb = runBlocking {
            val (_, fromDb) = interactor.getOccupation("saved-id")
            fromDb
        }

        coVerify(exactly = 0) { api.getActivity(allAny()) }
        coVerify(exactly = 1) { repository.getOccupation("saved-id") }
        assert(fromDb)
    }

    @Test
    fun `should save occupation`() {
        val (_, fromDb) = runBlocking {
            interactor.saveOccupation(testOccupation)
            interactor.getOccupation(testOccupation.key)
        }

        coVerify(exactly = 1) { repository.addOccupation(testOccupation) }
        assert(fromDb)
    }

    @Test
    fun `should delete saved occupation`() = runBlocking {
        val (_, fromDb) = runBlocking {
            interactor.saveOccupation(testOccupation)
            interactor.deleteOccupation(testOccupation)
            interactor.getOccupation(testOccupation.key)
        }

        coVerify(exactly = 1) { repository.deleteOccupation(testOccupation) }
        assert(!fromDb)
    }

    @Test
    fun `should return saved occupations from db`() = runBlocking {
        val occupations = runBlocking {
            interactor.saveOccupation(testOccupation)
            interactor.getSavedOccupations()
        }

        coVerify(exactly = 1) { repository.getAllOccupations() }
        assert(occupations.contains(testOccupation))
    }
}