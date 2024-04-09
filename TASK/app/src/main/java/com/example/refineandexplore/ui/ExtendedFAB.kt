package com.example.refineandexplore.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.refineandexplore.viewModel

@Composable
fun ExpandedButton(viewModel: viewModel) {

    Column(
        Modifier
            .size(250.dp, 380.dp)
        // You can add background color here if needed
    ) {

        Column {
            FAB_Elements(text = "Dating", icon = Icons.TwoTone.Favorite)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Matrimony", icon = Icons.TwoTone.Face)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Buy-Sell-Rent", icon = Icons.TwoTone.Home)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Business Cards", icon = Icons.TwoTone.Notifications)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Netclan Groups", icon = Icons.TwoTone.AccountCircle)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Jobs", icon = Icons.TwoTone.CheckCircle)
            Spacer(modifier = Modifier.size(15.dp))
            FAB_Elements(text = "Notes", icon = Icons.TwoTone.Share)
        }

    }
}

@Composable
fun FAB_Elements(text: String, icon: ImageVector) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    AnimatedVisibility(visibleState = state,
        enter = scaleIn()
    )

    {
        Row(
            modifier = Modifier
                .size(300.dp, 40.dp), // Apply alpha to the modifier
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            FloatingActionButton(
                onClick = { /*TODO*/ },
                content = {
                    Icon(
                        icon,
                        contentDescription = text
                    )
                },
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewExpandedButton() {
    ExpandedButton(viewModel())
}
