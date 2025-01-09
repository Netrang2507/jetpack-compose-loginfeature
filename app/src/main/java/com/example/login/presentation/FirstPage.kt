package com.example.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.login.R
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(navController: NavController) {


    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Button(

            onClick = {

                navController.navigate("loginscreen")


            },colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),

            modifier = Modifier.fillMaxWidth()
                .padding(120.dp,0.dp,120.dp,0.dp)

        ) {
            Text(stringResource(id = R.string.LoginHeading))
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {


                    navController.navigate("registrationPage") {


                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Red
            ),
            modifier = Modifier.fillMaxWidth()
                .padding(120.dp,0.dp,120.dp,0.dp)

        ) {
            Text(stringResource(id = R.string.Register))
        }
    }
}
