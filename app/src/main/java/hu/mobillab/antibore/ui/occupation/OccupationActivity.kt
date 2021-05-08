package hu.mobillab.antibore.ui.occupation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.ui.components.Loading
import hu.mobillab.antibore.ui.components.ThemeProvider
import javax.inject.Inject

@AndroidEntryPoint
class OccupationActivity : AppCompatActivity(), OccupationScreen {

    @Inject
    lateinit var occupationPresenter: OccupationPresenter

    private val occupation = mutableStateOf<Occupation?>(null)

    private val isOccupationSaved = mutableStateOf<Boolean>(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        occupationPresenter.getOccupation(intent.extras?.getString("id"))
        setContent {
            ThemeProvider {
                if (occupation.value == null) {
                    Loading()
                } else {
                    OccupationDetails(occupation.value!!)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        occupationPresenter.attachScreen(this)
    }

    override fun onStop() {
        occupationPresenter.detachScreen()
        super.onStop()
    }

    override fun showOccupationDetails(occupation: Occupation?) {
        this.occupation.value = occupation
    }

    override fun occupationSaved() {
        runOnUiThread {
            Toast.makeText(this, R.string.occupationSaved, Toast.LENGTH_SHORT).show()
        }
        isOccupationSaved.value = true
    }

    override fun occupationDeleted() {
        runOnUiThread {
            Toast.makeText(this, R.string.occupationDeleted, Toast.LENGTH_SHORT).show()
        }
        isOccupationSaved.value = false
    }

    override fun setOccupationSaved(saved: Boolean) {
        isOccupationSaved.value = saved
    }

    @Composable
    fun OccupationDetails(occupation: Occupation) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = getImageForCategory(occupation.type)),
                contentDescription = stringResource(R.string.imageForCategory),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.padding(8.dp, bottom = 30.dp)) {
            Text(
                text = occupation.activity,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
        }
        Row(
            modifier = Modifier.padding(10.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Groups,
                contentDescription = stringResource(R.string.participantsIcon),
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.participants),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = occupation.participants.toString())
        }
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ThumbUp,
                contentDescription = stringResource(R.string.accessibilityIcon),
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.accessibility),
                modifier = Modifier.padding(end = 10.dp)
            )
            Slider(
                value = occupation.accessibility?.toFloat() ?: 0.0f,
                enabled = false,
                onValueChange = { }
            )
        }
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Sell,
                contentDescription = stringResource(R.string.priceIcon),
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.price),
                modifier = Modifier.padding(end = 65.dp)
            )
            Slider(
                value = occupation.price?.toFloat() ?: 0.0f,
                enabled = false,
                onValueChange = { }
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            Divider(thickness = 1.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = {
                        if (!isOccupationSaved.value)
                            occupationPresenter.saveOccupation(occupation) else occupationPresenter.deleteOccupation(
                            occupation
                        )
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
                ) {
                    Text(
                        text = stringResource(
                            if (!isOccupationSaved.value)
                                R.string.saveForLater else R.string.deleteFromSaved
                        ),
                        modifier = Modifier.padding(5.dp),
                    )
                }
                TextButton(
                    onClick = { occupation.link?.let { occupationPresenter.openWebsite(it) } },
                    enabled = !occupation.link.isNullOrEmpty(),
                ) {
                    Text(
                        text = stringResource(R.string.goToPage),
                        modifier = Modifier.padding(5.dp),
                    )
                }
            }
        }
    }

    private fun getImageForCategory(type: Category): Int {
        return when (type) {
            Category.EDUCATION -> R.drawable.education
            Category.RECREATIONAL -> R.drawable.recreational
            Category.SOCIAL -> R.drawable.social
            Category.DIY -> R.drawable.diy
            Category.CHARITY -> R.drawable.charity
            Category.COOKING -> R.drawable.cooking
            Category.RELAXATION -> R.drawable.relaxation
            Category.MUSIC -> R.drawable.music
            Category.BUSYWORK -> R.drawable.busywork
        }
    }
}