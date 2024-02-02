package com.njm.mobilenewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.njm.mobilenewsapp.data.apiManager.ApiManager
import com.njm.mobilenewsapp.data.repositoryImpl.NewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.TheGuardianRespositoryImpl
import com.njm.mobilenewsapp.presentation.components.card.SmallCard
import com.njm.mobilenewsapp.presentation.screen.MainScreen
import com.njm.mobilenewsapp.presentation.ui.theme.MobileNewsAppTheme
import com.njm.mobilenewsapp.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD2D2)
                ) {
                    Column {
                        MainScreen()
                    }
                }
            }
        }
    }
}



//            LaunchedEffect(Unit){
//                println("================================================================================")
//                mainViewModel.getNews()
//            }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileNewsAppTheme {
        MainScreen()
    }
}