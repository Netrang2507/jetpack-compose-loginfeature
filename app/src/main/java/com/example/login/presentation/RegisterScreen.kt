package com.example.login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.model.UserDetails
import com.example.login.presentation.component.TextInputField
import com.example.login.utils.DataStoreManger
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val context = LocalContext.current
    val scope = rememberCoroutineScope()  // Create coroutine scope
    val dataStoreManger = DataStoreManger(context)
    var isRegistrationCompleted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.Register), style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(id = R.string.Name)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInputField(
            value =mobileNumber,
            onValueChange = {mobileNumber = it},
            label = stringResource(id = R.string.mobileNumber)

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInputField(
            value = email,
            onValueChange = {email = it},
            label = stringResource(id = R.string.EmailLabel),


        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (name.isEmpty() || mobileNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
                } else {
                    scope.launch {
                        dataStoreManger.saveToDataStore(
                            UserDetails(
                                emailAddress = email,
                                name = name,
                                mobileNumber = mobileNumber,
                                password = password
                            )
                        )

                        isRegistrationCompleted = true

                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btnsubtxt))
        }
        Button(
            onClick = {

                navController.navigate("loginscreen")


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.LoginHeading))
        }
        if (isRegistrationCompleted) {
            LaunchedEffect(isRegistrationCompleted) {
                navController.navigate("loginScreen")
            }
        }
    }
}


