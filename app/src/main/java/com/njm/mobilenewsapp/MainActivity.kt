package com.njm.mobilenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.njm.mobilenewsapp.data.apiManager.ApiManager
import com.njm.mobilenewsapp.data.repositoryImpl.ApiNewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.ApiNewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.ui.theme.MobileNewsAppTheme
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {

    private val apiNewsRepositoryImpl = ApiNewsRepositoryImpl(
        apiService = ApiManager.createApiNewsService()
    )

    private val apiNewYorkTimesRepositoryImpl = ApiNewYorkTimesRepositoryImpl(
        apiService = ApiManager.createApiNewYorkTimesService()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LaunchedEffect(Unit){
                println("================================================================================")
                //apiNewsRepositoryImpl.getNews()
                apiNewYorkTimesRepositoryImpl.getNews()
            }

            MobileNewsAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileNewsAppTheme {

    }
}