package hu.mobillab.antibore.ui.occupation

import android.content.Context
import android.content.Intent
import android.net.Uri
import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.ui.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OccupationPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) :
    Presenter<OccupationScreen?>() {

    fun getOccupation(key: String? = "") {
        CoroutineScope(Dispatchers.IO).launch {
            val (occupation, saved) = occupationInteractor.getOccupation(key ?: "")
            withContext(Dispatchers.Main) {
                screen?.showOccupationDetails(occupation)
                screen?.setOccupationSaved(saved)
            }
        }
    }

    fun openWebsite(url: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        (screen as Context).startActivity(webIntent)
    }

    fun saveOccupation(occupation: Occupation) {
        CoroutineScope(Dispatchers.IO).launch {
            occupationInteractor.saveOccupation(occupation)
            withContext(Dispatchers.Main) {
                screen?.occupationSaved()
            }
        }
    }

    fun deleteOccupation(occupation: Occupation) {
        CoroutineScope(Dispatchers.IO).launch {
            occupationInteractor.deleteOccupation(occupation)
            withContext(Dispatchers.Main) {
                screen?.occupationDeleted()
            }
        }
    }
}