package com.example.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.dataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.FirstScreen
import com.example.login.presentation.HomeScreen
import com.example.login.presentation.LoginScreen

import com.example.login.presentation.RegistrationScreen
import com.example.login.ui.theme.LoginTheme
import com.example.login.utils.Constants
import com.example.login.utils.DataStoreManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginTheme {
             MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreManger = DataStoreManger(context )
    val userDetails = dataStoreManger.getFromDataStore().collectAsState(null)
    LaunchedEffect(userDetails.value) {
        if (userDetails.value?.emailAddress?.isNotEmpty() == true) {

            navController.navigate("homeScreen") {

            }
        }
    }

    if (userDetails.value?.emailAddress?.isNotEmpty()==true){
       navController.navigate("homeScreen")
   }

    NavHost(
        navController = navController,
        startDestination ="firstScreen",
    ) {
        composable("firstScreen") {
            FirstScreen(navController = navController)
        }
        composable(Constants.registrationPage) {
            RegistrationScreen(navController = navController)
        }
        composable(Constants.loginScreen) {
            LoginScreen(navController = navController)
        }
        composable(Constants.HomeScreen) {
            HomeScreen(navController = navController)
        }
    }
}

