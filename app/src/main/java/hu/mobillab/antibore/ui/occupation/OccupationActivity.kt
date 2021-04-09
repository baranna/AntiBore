package hu.mobillab.antibore.ui.occupation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Occupation
import javax.inject.Inject

@AndroidEntryPoint
class OccupationActivity : AppCompatActivity(), OccupationScreen {

    @Inject
    lateinit var occupationPresenter: OccupationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occupation)
    }

    override fun onStart() {
        super.onStart()
        occupationPresenter.attachScreen(this)
    }

    override fun onStop() {
        occupationPresenter.detachScreen()
        super.onStop()
    }

    override fun showOccupationDetails(occupation: Occupation) {
        TODO("Not yet implemented")
    }
}