package hu.mobillab.antibore.unit

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.repository.OccupationRepository
import hu.mobillab.antibore.ui.main.MainPresenter
import hu.mobillab.antibore.unit.mock.MockOccupationApi
import hu.mobillab.antibore.unit.mock.MockOccupationDAO
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    private lateinit var interactor: OccupationInteractor

    @Before
    fun setup() {
        interactor = spyk(
            OccupationInteractor(
                MockOccupationApi(),
                OccupationRepository(MockOccupationDAO())
            )
        )
        presenter = MainPresenter(interactor)
    }

    @Test
    fun `should get new occupations`() {
        runBlocking {
            presenter.getNewOccupations()
        }

        coVerify(exactly = 1) { interactor.getOccupations() }
    }

    @Test
    fun `should get new occupations when not showing only saved`() {
        runBlocking {
            presenter.onShowOnlySavedChanged(false)
        }

        coVerify(exactly = 1) { interactor.getOccupations() }
        coVerify(exactly = 0) { interactor.getSavedOccupations() }
    }

    @Test
    fun `should get saved occupations when showing only saved`() {
        runBlocking {
            presenter.onShowOnlySavedChanged(true)
        }

        coVerify(exactly = 1) { interactor.getSavedOccupations() }
        coVerify(exactly = 0) { interactor.getOccupations() }
    }

    @Test
    fun `should get saved occupations`() {
        runBlocking {
            presenter.getSavedOccupations()
        }

        coVerify(exactly = 1) { interactor.getSavedOccupations() }
    }
}