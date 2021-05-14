package hu.mobillab.antibore.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.mobillab.antibore.R

@Composable
@Preview
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(10.dp, 0.dp),
                style = MaterialTheme.typography.h6
            )
        },
        actions = {
            IconButton(onClick = { TODO() }) {
                Icon(
                    imageVector = Icons.Outlined.BugReport,
                    contentDescription = "Make me crash"
                )
            }
        }
    )
}