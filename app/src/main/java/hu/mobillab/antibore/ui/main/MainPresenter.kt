package hu.mobillab.antibore.ui.main

import android.content.Context
import android.content.Intent
import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.ui.Presenter
import hu.mobillab.antibore.ui.occupation.OccupationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) :
    Presenter<MainScreen?>() {

    fun getNewOccupations() {
        CoroutineScope(Dispatchers.IO).launch {
            val occupations = occupationInteractor.getOccupations()
            withContext(Dispatchers.Main) {
                screen?.showOccupations(occupations)
            }
        }
    }

    fun onOccupationClicked(id: String) {
        val intent = Intent(screen as Context, OccupationActivity::class.java);
        intent.putExtra("id", id)
        (screen as Context).startActivity(intent)
    }

    fun onShowOnlySavedChanged(showOnlySaved: Boolean) {
        screen?.showOccupations(listOf())
        if (showOnlySaved)
            getSavedOccupations()
        else
            getNewOccupations()
    }

    fun getSavedOccupations() {
        CoroutineScope(Dispatchers.IO).launch {
            val occupations = occupationInteractor.getSavedOccupations()
            withContext(Dispatchers.Main) {
                screen?.showOccupations(occupations)
            }
        }
    }
}