package com.example.mygarden.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InfoItem(
    label: String,
    info: String,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label)
            Text(text = info)
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun InfoItemPreview() {
    InfoItem(label = "Часник", info = "Овоч")
}