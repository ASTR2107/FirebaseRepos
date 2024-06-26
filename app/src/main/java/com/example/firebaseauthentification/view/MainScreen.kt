package com.example.firebaseauthentification.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebaseauthentification.domain.model.model.data.Book
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun MainScreen() {
    val firebaseFirestore = Firebase.firestore
    val list = remember {
        mutableStateOf(emptyList<Book>())
    }
    firebaseFirestore.collection("books").get().addOnCompleteListener { task ->
        if (task.isSuccessful){
            list.value = task.result.toObjects(Book::class.java)

        }else {
            task.exception
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            items(list.value) { book ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = book.name.toString(),
                        modifier = Modifier.fillMaxWidth().wrapContentWidth())
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            firebaseFirestore.collection("Books")
                .document().set(
                    Book(
                        "My Book",
                        "Ser",
                        "100",
                        "fiction",
                        ""
                    )
                )

        }
        ) {
            Text(text = "Add Book")

        }

    }
}