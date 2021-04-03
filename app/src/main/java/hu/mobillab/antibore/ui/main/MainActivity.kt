package hu.mobillab.antibore.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Occupation

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showOccupations(occupationsList: List<Occupation>) {
        TODO("Not yet implemented")
    }
}