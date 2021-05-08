package hu.mobillab.antibore.ui.occupation

import android.content.Context
import android.content.Intent
import android.net.Uri
import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.ui.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OccupationPresenter @Inject constructor(var occupationInteractor: OccupationInteractor) :
    Presenter<OccupationScreen?>() {

    fun getOccupation(key: String? = "") {
        CoroutineScope(Dispatchers.IO).launch {
            val occupation = occupationInteractor.getOccupation(key)
            screen?.showOccupationDetails(occupation)
        }
    }

    fun openWebsite(url: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        (screen as Context).startActivity(webIntent)
    }
}