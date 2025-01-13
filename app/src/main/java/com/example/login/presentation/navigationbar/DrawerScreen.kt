package com.example.login.presentation.navigationbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.R
import kotlinx.coroutines.launch
import com.example.login.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(-1) }
        var selectedItemIndex1 by rememberSaveable { mutableIntStateOf(-1) }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index

                                scope.launch { drawerState.close() }
                                when (index) {
                                    0 -> {navController.navigate(Constants.HomeScreen)
                                        selectedItemIndex= 0}
                                    1 -> {
                                        navController.navigate(Constants.infoScreen)
                                        selectedItemIndex= 1 }
                                    2 -> {navController.navigate(Constants.Security)
                                        selectedItemIndex= 2}
                                    3 -> {navController.navigate(Constants.Setting)
                                        selectedItemIndex= 3}

                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        selectedItemIndex = selectedItemIndex1,
                        onItemSelected = { index ->
                            selectedItemIndex1 = index
                        }
                    )
                },
                topBar = {
                    TopAppBar(

                        title = {
                            Row(modifier = Modifier
                                .padding(0.dp,27.dp,0.dp,0.dp)

                            ) {
                                Text(text = stringResource(id = R.string.LoginApp))
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                            }
                        }
                    )
                }
            ) {
                content()
            }
        }
    }
}
