package com.example.mygarden.gardens.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mygarden.R

@Composable
fun GardenItem(modifier: Modifier = Modifier) {

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.splash_icon), contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = "My garden",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "This is my garden with many of plants. The garden is cool",
                    fontSize = 16.sp
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                  Text(text = "20.05.2021")
            }
        }


    }

}

@Preview(showSystemUi = true)
@Composable
fun GardenItemPreview() {
    GardenItem()
}