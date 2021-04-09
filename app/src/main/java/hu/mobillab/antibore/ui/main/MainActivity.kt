package hu.mobillab.antibore.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.ui.occupation.OccupationPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        mainPresenter.detachScreen()
        super.onStop()
    }

    override fun showOccupations(occupationsList: List<Occupation>) {
        TODO("Not yet implemented")
    }
}