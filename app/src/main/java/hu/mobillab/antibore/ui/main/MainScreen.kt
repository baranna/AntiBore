package hu.mobillab.antibore.ui.main

import hu.mobillab.antibore.model.Occupation

interface MainScreen {
    fun showOccupations(occupationsList: List<Occupation>)
}