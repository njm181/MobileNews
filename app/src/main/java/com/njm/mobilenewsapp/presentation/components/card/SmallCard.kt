package com.njm.mobilenewsapp.presentation.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.mobilenewsapp.presentation.ui.theme.MobileNewsAppTheme

@Composable
fun SmallCard(){
    Card(
        modifier = Modifier
        .width(120.dp)
        .height(50.dp)
        .fillMaxSize()
        .padding(start = 8.dp, end = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(4.dp)) {
            Text(
                text = "Section Name",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title title",
                fontSize = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}


@Composable
@Preview
fun SmallCardPreview(){
    MobileNewsAppTheme {
        SmallCard()
    }
}