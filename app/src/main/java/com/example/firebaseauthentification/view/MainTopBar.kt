@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.firebaseauthentification.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun MainTopBar() {
    val coroutine = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    Scaffold(
        topBar = {
            val drawerValue = remember { mutableStateOf(DrawerValue.Closed) }
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("IKRA", fontSize = 22.sp) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Меню"
                        )
                    }
                },
                actions = {
                    IconButton({ }) { Icon(Icons.Filled.Info, contentDescription = "О приложении") }
                    IconButton({ }) { Icon(Icons.Filled.Search, contentDescription = "Поиск") }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    navigationIconContentColor = Color.LightGray,
                    actionIconContentColor = Color.LightGray
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray,
                contentColor = Color.LightGray
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Избранное"
                    )
                }
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Info,
                        contentDescription = "Справка"
                    )
                }
            }
        }
    ) {
        Text("Hello", fontSize = 28.sp, modifier = Modifier.padding(it))
    }
}


@Composable
fun NavDrawer() {
    val items = listOf("Menu", "Contact", "QAbout")
    val selectedItem = remember { mutableStateOf(items[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            items.forEach { item ->
                NavigationDrawerItem(label = { Text(text = item, fontSize = 22.sp) },
                    selected = selectedItem.value == item,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    }
                )
            }
        }
    }
}



