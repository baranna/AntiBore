package hu.mobillab.antibore.ui.main

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.ui.Presenter
import javax.inject.Inject

class MainPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) : Presenter<MainScreen?> () {

    suspend fun getOccupations()  {
        val occupations = occupationInteractor.getOccupations() {
            screen?.showOccupations(it)
        }
    }
}