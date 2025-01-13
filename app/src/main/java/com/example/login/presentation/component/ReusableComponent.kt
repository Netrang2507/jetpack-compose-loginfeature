package com.example.login.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp

@Composable
fun TextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,


    ) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),


        )
}


@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Blue,  // Default color, you can override if needed
    contentColor: Color = Color.White

) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ), modifier = modifier

    ) {
        Text(text)
    }
}

@Composable
fun TextEditField(
    value: String, onValueChange: (String) -> Unit, label: String, placeholder: String

) {

    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder) }


    )
}