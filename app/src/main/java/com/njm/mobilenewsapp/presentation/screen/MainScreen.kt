package com.njm.mobilenewsapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.mobilenewsapp.presentation.components.card.LargeCard
import com.njm.mobilenewsapp.presentation.components.card.SmallCard
import com.njm.mobilenewsapp.presentation.viewModel.MainViewModel
import com.njm.mobilenewsapp.presentation.viewModel.SharedViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.njm.mobilenewsapp.domain.model.newYorkTimes.NewYorkTimes
import com.njm.mobilenewsapp.domain.model.news.News
import com.njm.mobilenewsapp.domain.model.theGuardian.TheGuardian


@Composable
fun MainScreen(mainViewModel: MainViewModel?, sharedViewModel: SharedViewModel?) {
    val loadingState = mainViewModel?.loadingState?.observeAsState()?.value
    val newsState = mainViewModel?.newsState?.observeAsState()?.value
    val newYorkTimesState = mainViewModel?.newYorkTimesState?.observeAsState()?.value
    val theGuardianState = mainViewModel?.theGuardianState?.observeAsState()?.value

    LaunchedEffect(true){
        mainViewModel?.getNews()
    }
//    val newState = sharedViewModel?.newsState?.collectAsState() ******
//    LaunchedEffect(newState){
//        println(newState)
//    }
//
//    Button(onClick = {
//        sharedViewModel?.startMyWorker()
//    }) {
//        Text(text = "data ====> ${newState?.value}")
//    }
    MainScreenContent(
        loadingState = loadingState,
        newsState = newsState,
        newYorkTimesState = newYorkTimesState,
        theGuardianState = theGuardianState
    )
}

@Composable
fun MainScreenContent(
    loadingState: Boolean?,
    newsState: News?,
    newYorkTimesState: NewYorkTimes?,
    theGuardianState: TheGuardian?
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (loadingState == true){
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = Color.Gray
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = newsState?.articles?.size.toString(),//"Mobile News",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = newYorkTimesState?.results?.size.toString(),//"Mobile News",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                text = theGuardianState?.response?.results?.size.toString(),//"Mobile News",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow {
                items(10) {
                    SmallCard()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(10){
                    LargeCard()
                }
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview(){
    MaterialTheme {
        MainScreen(null, null)
    }
}