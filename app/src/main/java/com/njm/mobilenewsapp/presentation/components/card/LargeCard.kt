package com.njm.mobilenewsapp.presentation.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.njm.mobilenewsapp.R

@Composable
fun LargeCard(
    sectionName: String,
    imageUrl: String,
    description: String,
    webUrl: String
){
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(260.dp)
            .fillMaxSize()
            .padding(vertical = 4.dp)
            .clickable {
                uriHandler.openUri(webUrl)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = sectionName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color.Gray),
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )


            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                text = description,
                fontSize = 10.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
@Preview
fun LargeCardPreview(){
    MaterialTheme {
        LargeCard(
            sectionName = "Section Name",
            imageUrl = "",
            description = "",
            webUrl = ""
        )
    }
}