package hu.mobillab.antibore.ui.occupation

import hu.mobillab.antibore.model.Occupation

interface OccupationScreen {
    fun showOccupationDetails(occupation: Occupation?)
}