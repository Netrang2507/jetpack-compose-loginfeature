package com.example.login.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.presentation.component.TextInputField
import com.example.login.utils.DataStoreManger
import com.example.login.R
import com.example.login.model.UserDetails
import com.example.login.presentation.component.CommonButton
import com.example.login.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManger(context)
    val scope = rememberCoroutineScope()

    val savedUserDetails =
        dataStoreManager.getFromDataStore().collectAsState(initial = UserDetails())


    var user by remember { mutableStateOf(savedUserDetails.value) }


    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    if (savedUserDetails.value != user) {
        user = savedUserDetails.value
        name = user.name
        email = user.emailAddress
        mobileNumber = user.mobileNumber
        password = user.password


    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.Edit)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextInputField(
                value = name,
                onValueChange = { name = it },
                label = stringResource(id = R.string.Name),

                )

            Spacer(modifier = Modifier.height(6.dp))

            TextInputField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(id = R.string.EmailLabel)
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextInputField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = stringResource(id = R.string.mobileNumber)
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.UpPass)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            CommonButton(
                onClick = {
                    scope.launch {
                        dataStoreManager.saveToDataStore(
                            userDetails = UserDetails(
                                name = name,
                                emailAddress = email,
                                password = password,
                                mobileNumber = mobileNumber
                            )
                        )
                        navController.navigate(Constants.HomeScreen)
                    }
                }, modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.btnsubtxt)
            )
        }
    }
}
