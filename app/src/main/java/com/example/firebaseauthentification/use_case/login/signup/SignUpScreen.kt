@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.firebaseauthentification.use_case.login.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebaseauthentification.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val showBottomSheet by remember { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Зарегестрироваться")

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, modifier = Modifier
            .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue
            ),
            placeholder = { Text(text = "Email") }

        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, modifier = Modifier
            .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue
            ),
            placeholder = { Text(text = "Password") }

        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    viewModel.singUpUser(email, password)
                }
            }

        ) {
            Text(text = "Регистрация")
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            if (state.value?.isLoading == true){
                CircularProgressIndicator()

            }

        }
        Text(text = "У вас уже есть профиль ? Регистрация ",
            fontWeight = FontWeight.Bold,
            color = Color.Blue)

        Text(text = "Зайти с помощью ",
            fontWeight = FontWeight.Medium,
            color = Color.Blue)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                
            }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Icon",
                    modifier = Modifier.size(50.dp))

            }

            Spacer(modifier = Modifier.height(25.dp))

            IconButton(onClick = {

            }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Icon",
                    modifier = Modifier.size(50.dp))

            }

            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true){
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "${success}",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true){
                        val error= state.value?.isError
                        Toast.makeText(context, "${error}",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }


        }
    }
}

