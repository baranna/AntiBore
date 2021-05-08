package hu.mobillab.antibore.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import hu.mobillab.antibore.R
import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.ui.components.Loading
import hu.mobillab.antibore.ui.components.ThemeProvider
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter

    private val occupations = mutableStateListOf<Occupation>()

    private val isRefreshing = mutableStateOf(true)

    private val showOnlySaved = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter.getNewOccupations()
        setContent {
            ThemeProvider {
                Header()
                if (occupations.size == 0) {
                    Loading()
                } else {
                    OccupationList(occupations)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        mainPresenter.detachScreen()
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        if (showOnlySaved.value)
            mainPresenter.getSavedOccupations()
    }

    override fun showOccupations(occupationsList: List<Occupation?>) {
        occupations.clear()
        occupations.addAll(occupationsList.filterNotNull())
        isRefreshing.value = false
    }

    @Composable
    fun Header() {
        Row {
            Card(
                elevation = 3.dp,
                shape = RoundedCornerShape(3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 5.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(
                            if (!showOnlySaved.value)
                                R.string.randomOccupations
                            else R.string.savedOccupations
                        ),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, end = 8.dp)
                    ) {
                        Switch(
                            checked = showOnlySaved.value,
                            onCheckedChange = {
                                showOnlySaved.value = it; mainPresenter.onShowOnlySavedChanged(it)
                            }
                        )
                        Text(
                            text = stringResource(R.string.showOnlySaved),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun OccupationList(
        occupations: SnapshotStateList<Occupation>
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing.value),
            onRefresh = {
                isRefreshing.value = true;
                showOnlySaved.value = false;
                mainPresenter.getNewOccupations()
            }
        ) {
            LazyColumn() {
                items(occupations) {
                    OccupationListItem(occupation = it)
                }
            }
        }
    }

    @Composable
    fun OccupationListItem(occupation: Occupation) {
        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .clickable { mainPresenter.onOccupationClicked(occupation.key) }
                .fillMaxWidth()
                .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 5.dp)
                .defaultMinSize(minHeight = 65.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = getIconForCategory(occupation.type),
                    contentDescription = stringResource(R.string.categoryIcon),
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(start = 2.dp)
                )
                Text(
                    text = occupation.activity,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
    }

    private fun getIconForCategory(type: Category): ImageVector {
        return when (type) {
            Category.EDUCATION -> Icons.Outlined.School
            Category.RECREATIONAL -> Icons.Outlined.SelfImprovement
            Category.SOCIAL -> Icons.Outlined.Groups
            Category.DIY -> Icons.Outlined.Handyman
            Category.CHARITY -> Icons.Outlined.VolunteerActivism
            Category.COOKING -> Icons.Outlined.RamenDining
            Category.RELAXATION -> Icons.Outlined.Spa
            Category.MUSIC -> Icons.Outlined.MusicNote
            Category.BUSYWORK -> Icons.Outlined.HistoryEdu
        }
    }
}