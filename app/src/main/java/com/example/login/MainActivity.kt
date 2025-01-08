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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.HomeScreen
import com.example.login.presentation.LoginScreen

import com.example.login.presentation.RegistrationScreen
import com.example.login.ui.theme.LoginTheme
import com.example.login.utils.Constants

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

@SuppressLint("SuspiciousIndentation")
@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.registrationPage,
    ) {
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
