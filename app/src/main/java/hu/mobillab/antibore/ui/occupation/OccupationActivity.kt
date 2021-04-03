package hu.mobillab.antibore.ui.occupation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Occupation

class OccupationActivity : AppCompatActivity(), OccupationScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occupation)
    }

    override fun showOccupationDetails(occupation: Occupation) {
        TODO("Not yet implemented")
    }
}