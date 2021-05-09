package hu.mobillab.antibore.unit

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.repository.OccupationRepository
import hu.mobillab.antibore.ui.occupation.OccupationPresenter
import hu.mobillab.antibore.unit.mock.MockOccupationApi
import hu.mobillab.antibore.unit.mock.MockOccupationDAO
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test

class OccupationPresenterTest {

    private lateinit var presenter: OccupationPresenter

    private lateinit var interactor: OccupationInteractor

    private lateinit var testOccupation: Occupation

    @Before
    fun setup() {
        interactor = spyk(
            OccupationInteractor(
                MockOccupationApi(),
                OccupationRepository(MockOccupationDAO())
            )
        )
        presenter = OccupationPresenter(interactor)
        testOccupation =
            Occupation(key = "test-id", "test", null, Category.BUSYWORK, null, null, null)
    }

    @Test
    fun `should get occupation with given key`() {
        runBlocking {
            presenter.getOccupation("key")
        }

        coVerify(exactly = 1) { interactor.getOccupation("key") }
    }

    @Test
    fun `should save occupation`() {
        runBlocking {
                presenter.saveOccupation(testOccupation)
            }

        coVerify(exactly = 1) { interactor.saveOccupation(testOccupation) }
    }

    @Test
    fun `should delete occupation`() {
        runBlocking {
            presenter.deleteOccupation(testOccupation)
        }

        coVerify(exactly = 1) { interactor.deleteOccupation(testOccupation) }
    }
}