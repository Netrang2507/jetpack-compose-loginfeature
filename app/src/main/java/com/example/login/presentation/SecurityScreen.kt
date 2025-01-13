package com.example.login.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.model.UserDetails
import com.example.login.presentation.component.CommonButton
import com.example.login.utils.Constants
import com.example.login.utils.DataStoreManger

@Composable
fun securityScreen(navController: NavController){
    val context = LocalContext.current
    val dataStoreManager = DataStoreManger(context)
    val scope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    val savedUserDetails = dataStoreManager.getFromDataStore().collectAsState(initial = UserDetails())


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.Password)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(6.dp))

        CommonButton(
            onClick = {
                val pass = savedUserDetails.value
                if(pass.password ==password) {
                    navController.navigate(Constants.Edit)
                }else{
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(120.dp, 0.dp, 120.dp, 0.dp),
            text = stringResource(id = R.string.btnsubtxt)
        )
        Spacer(modifier = Modifier.height(6.dp))

    }
}



