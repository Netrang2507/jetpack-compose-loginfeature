package com.example.login.presentation.navigationbar


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import com.example.login.utils.Constants

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {

        boItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onItemSelected(index)
                    when (index) {
                        0 -> navController.navigate(Constants.HomeScreen)
                        1 -> navController.navigate(Constants.Security)
                        2 -> navController.navigate(Constants.Setting)
                    }
                },
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}
