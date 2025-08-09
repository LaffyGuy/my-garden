package com.example.mygarden.gardens.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun GardenItem(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    imageUri: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (imageUri.isNotBlank()) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Image", color = Color.White)
                }
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    fontSize = 16.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "20.05.2021")
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun GardenItemPreview() {
    GardenItem(
        name = "dsdsds",
        description = "sdsdsds",
        imageUri = "sdsdsdsds"
    )
}