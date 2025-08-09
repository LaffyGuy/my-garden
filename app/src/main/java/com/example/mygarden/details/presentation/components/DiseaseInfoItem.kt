package com.example.mygarden.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mygarden.R

@Composable
fun DiseaseInfoItem(
    image: String,
    name: String
) {
    Card(
        modifier = Modifier.size(150.dp, 200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(painter = painterResource(id = R.drawable.splash_icon), contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = name)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DiseaseInfoItemPreview() {
    DiseaseInfoItem("", "SDsdsds")
}