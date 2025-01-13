package com.example.login.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.presentation.component.CommonButton
import com.example.login.utils.Constants
import com.example.login.utils.DataStoreManger

import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreManger = DataStoreManger(context)

    // Collect user data from DataStore
    val userDetails = dataStoreManger.getFromDataStore().collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.HomePagetitle),
            style = TextStyle(
                fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal,
                fontSize = 22.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display user details
        userDetails.value?.let {
            Text("Name: ${it.name}")
            Text("Mobile: ${it.mobileNumber}")
            Text("Email: ${it.emailAddress}")
        }
        Spacer(modifier = Modifier.height(6.dp))

        CommonButton(
            onClick = {
                navController.navigate(Constants.Security)

            },
            modifier = Modifier.fillMaxWidth()
                .height(40.dp),
            text = stringResource(id = R.string.Edit)
        )
        Spacer(modifier = Modifier.height(6.dp))

        CommonButton(
            onClick = {
                scope.launch {

                    dataStoreManger.clearDataStore()
                    navController.navigate(Constants.registrationPage) {

                    }
                }
            },
            modifier =
            Modifier.fillMaxWidth(),
            text = stringResource(R.string.DeteleUSer)
        )

        Spacer(modifier = Modifier.height(6.dp))
        CommonButton(
            onClick = {
                scope.launch {

                    navController.navigate(Constants.loginScreen) {
                        popUpTo(Constants.HomeScreen) { inclusive = true }
                    }
                }
            }, modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Red,
            text = stringResource(id = R.string.Logout)
        )

    }

}
