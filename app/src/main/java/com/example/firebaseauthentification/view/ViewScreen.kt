package com.example.firebaseauthentification.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import com.example.firebaseauthentification.viewModel.MainViewModel

@Composable
fun SuccessScreen(postResponse: List<PostResponse>, mainViewModel: MainViewModel) {
    Column() {
        Row(modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = {mainViewModel.andPost()}
            ) {
                Text(text = "Post")
            }

            Button(onClick = {mainViewModel.putPost()}
            ) {
                Text(text = "Put")
            }

            Button(onClick = {mainViewModel.patchPost()}
            ) {
                Text(text = "Patch")
            }


        }
        LazyColumn {
            items(postResponse){
                    item ->
                PostItem(item)
            }
        }

    }
}

@Composable
fun PostItem(item: PostResponse) {
    val title = item.title ?: ""
    val body = item.body ?: ""

    Row (modifier = Modifier
        .padding(vertical = 20.dp,
            horizontal = 25.dp)
    ) {
        Text(text = item.id.toString(), fontSize = 24.sp)
        Column(modifier = Modifier
            .padding(start = 15.dp )
        ) {
            Text(text = title)
            Text(text = body)

        }

    }

}
