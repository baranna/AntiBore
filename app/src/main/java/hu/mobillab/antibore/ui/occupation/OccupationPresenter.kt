package hu.mobillab.antibore.ui.occupation

import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.ui.Presenter
import javax.inject.Inject

 class OccupationPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) : Presenter<OccupationScreen?>() {

     fun getOccupation(key: String) {
         return occupationInteractor.getOccupation(key)
     }
 }