package hu.mobillab.antibore.ui.main

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.ui.Presenter
import javax.inject.Inject

class MainPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) : Presenter<MainScreen?> () {

    fun getOccupations()  {
        return occupationInteractor.getOccupations()
    }
}