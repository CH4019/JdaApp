package com.ch4019.jdaapp.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.components.CardButton
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@Composable
fun LoginView() {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var isShow by remember{ mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("用户名") },
            shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = passWord,
            onValueChange = { passWord = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { isShow = !isShow }) {
                    if (isShow){ Icon(imageVector = Icons.Rounded.Visibility, contentDescription = null,tint = MaterialTheme.colorScheme.primary) }
                    else{ Icon(imageVector = Icons.Rounded.VisibilityOff, contentDescription = null) }
                }
            },
            visualTransformation = if (isShow) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("密码") },
            shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        CardButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {

                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "登录")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    JdaAppTheme {
        LoginView()
    }
}