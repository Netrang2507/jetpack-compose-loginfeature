package com.example.login.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.utils.Constants


@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonButton(
            onClick = {
                navController.navigate(Constants.loginScreen)
            },
            containerColor = Color.Blue,
            contentColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(120.dp, 0.dp, 120.dp, 0.dp),
            text = stringResource(id = R.string.LoginHeading)
        )

        Spacer(modifier = Modifier.height(24.dp))

        CommonButton(
            onClick = {
                navController.navigate(Constants.registrationPage)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(120.dp, 0.dp, 120.dp, 0.dp),
            text = stringResource(id = R.string.Register)
        )
    }
}
