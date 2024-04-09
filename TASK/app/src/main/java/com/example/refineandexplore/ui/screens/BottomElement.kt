package com.example.refineandexplore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun BottomElement(text: String, icon: ImageVector) {
    ConstraintLayout(modifier = Modifier) {
        val (iconRef, textRef) = createRefs()
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
            )
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(4.dp)
                .offset(y = (-20).dp)
                .constrainAs(textRef) {
                    top.linkTo(iconRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview
@Composable
fun BottomElementPreview() {
    BottomElement(text = "Dating", icon = Icons.TwoTone.Favorite)
}