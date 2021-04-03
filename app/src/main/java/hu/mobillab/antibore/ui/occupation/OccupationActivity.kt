package hu.mobillab.antibore.ui.occupation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.ui.main.MainPresenter

@AndroidEntryPoint
class OccupationActivity : AppCompatActivity(), OccupationScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occupation)
    }

    override fun onStart() {
        super.onStart()
        OccupationPresenter.attachScreen(this)
    }

    override fun onStop() {
        OccupationPresenter.detachScreen()
        super.onStop()
    }

    override fun showOccupationDetails(occupation: Occupation) {
        TODO("Not yet implemented")
    }
}