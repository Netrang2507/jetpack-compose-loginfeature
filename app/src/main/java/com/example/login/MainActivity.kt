package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.navigationbar.NavigationDrawer
import com.example.login.presentation.EditScreen
import com.example.login.presentation.component.FirstScreen
import com.example.login.presentation.HomeScreen
import com.example.login.presentation.navigationbar.InfoScreen
import com.example.login.presentation.LoginScreen

import com.example.login.presentation.RegistrationScreen
import com.example.login.presentation.navigationbar.SettingScreen

import com.example.login.presentation.securityScreen
import com.example.login.ui.theme.LoginTheme
import com.example.login.utils.Constants
import com.example.login.utils.DataStoreManger


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
    val dataStoreManger = DataStoreManger(context)
    val userDetails = dataStoreManger.getFromDataStore().collectAsState(null)

    LaunchedEffect(userDetails.value) {
        if (userDetails.value?.emailAddress?.isNotEmpty() == true) {
            navController.navigate(Constants.HomeScreen)
        }
    }



    NavHost(
        navController = navController,
        startDestination = Constants.FirstScreen
    ) {
        composable(Constants.FirstScreen) {
            FirstScreen(navController = navController)
        }
        composable(Constants.registrationPage) {
            RegistrationScreen(navController = navController)
        }
        composable(Constants.loginScreen) {
            LoginScreen(navController = navController)
        }
        composable(Constants.HomeScreen) {
            NavigationDrawer(navController = navController) {
                HomeScreen(navController = navController)
            }
        }
        composable(Constants.infoScreen) {
            NavigationDrawer(navController = navController) {
                InfoScreen(navController = navController)
            }
        }
        composable(Constants.Security) {
            NavigationDrawer(navController = navController) {
                securityScreen(navController = navController)
            }
        }
        composable(Constants.Edit) {
            NavigationDrawer(navController = navController) {
                EditScreen(navController = navController)
            }
        }
        composable(Constants.Setting) {
            NavigationDrawer(navController = navController) {
                SettingScreen(navController = navController)
            }
        }
    }
}


