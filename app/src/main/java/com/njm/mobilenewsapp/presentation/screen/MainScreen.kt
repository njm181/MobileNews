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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.njm.mobilenewsapp.presentation.components.card.LargeCard
import com.njm.mobilenewsapp.presentation.components.card.SmallCard
import com.njm.mobilenewsapp.presentation.viewModel.MainViewModel
import com.njm.mobilenewsapp.presentation.viewModel.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*


@Composable
fun MainScreen(mainViewModel: MainViewModel?, sharedViewModel: SharedViewModel?) {

    val newState = sharedViewModel?.newsState?.collectAsState()
    LaunchedEffect(newState){
        println(newState)
    }

    Button(onClick = {
        sharedViewModel?.startMyWorker()
    }) {
        Text(text = "data ====> ${newState?.value}")
    }
    //MainScreenContent(loadingState, dataModelState.toString())
}

@Composable
fun MainScreenContent(loadingState: Boolean?, dataModelState: String?) {

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
                text = "dataModelState".toString(),//"Mobile News",
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