package com.example.login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.utils.DataStoreManger
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.example.login.model.UserDetails
import com.example.login.presentation.component.TextInputField
import com.example.login.R

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()  // Create coroutine scope
    val dataStoreManger = DataStoreManger(context)

    // Collect user data from DataStore
    val savedUserDetails = dataStoreManger.getFromDataStore().collectAsState(initial = UserDetails("", "", "", ""))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.LoginHeading))

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        TextInputField(
            value = email,
            onValueChange = {email = it},
            label = stringResource(id = R.string.EmailLabel),


        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
                } else {

                    scope.launch {
                        val savedUser = savedUserDetails.value
                         if (savedUser.emailAddress == email && savedUser.password == password) {
                                navController.navigate("homeScreen")
                            } else {
                                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        }

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.LoginHeading))
        }

        Button(
            onClick = {
                scope.launch {

                    navController.navigate("registrationPage") {

                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.Register))
        }
    }
}
