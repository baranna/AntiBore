package hu.mobillab.antibore.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import hu.mobillab.antibore.R

@Composable
fun ThemeProvider(body: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = colorResource(R.color.primary),
            primaryVariant = colorResource(R.color.primaryVariant),
            secondary = colorResource(R.color.secondary),
            secondaryVariant = colorResource(R.color.secondaryVariant),
        )
    )
    {
        Column {
            TopBar()
            body()
        }
    }
}