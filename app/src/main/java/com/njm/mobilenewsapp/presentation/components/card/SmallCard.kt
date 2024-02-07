package com.njm.mobilenewsapp.presentation.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.mobilenewsapp.domain.model.theGuardian.Result
import com.njm.mobilenewsapp.presentation.ui.theme.MobileNewsAppTheme

@Composable
fun SmallCard(result: Result?) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(60.dp)
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .clickable { uriHandler.openUri(result?.webUrl.toString()) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 4.dp, start = 4.dp, end = 2.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = result?.sectionName.toString(),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )
            Text(
                text = result?.webTitle.toString(),
                fontSize = 8.sp,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 15.sp
            )
        }
    }

}


@Composable
@Preview
fun SmallCardPreview(){
    MobileNewsAppTheme {
        SmallCard(null)
    }
}