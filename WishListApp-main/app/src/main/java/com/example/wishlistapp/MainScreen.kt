package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(viewModel: ShoppingViewModel, navController: NavController) {
    val categories by viewModel.categories.collectAsState()
    LaunchedEffect(Unit) {viewModel.getAllCategories() }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Список категорий", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {navController.navigate(Screen.CATEGORY_SCREEN_NEW.route)}) {Text("Добавить категорию")}
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(categories) { category ->
                Row( modifier = Modifier.fillMaxWidth().padding(8.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = category.name,modifier = Modifier.weight(1f),style = MaterialTheme.typography.bodyLarge )
                    IconButton(onClick = {
                        val route = Screen.EDIT_CATEGORY_SCREEN.route.replace("{categoryId}", category.id.toString())
                        navController.navigate(route)
                    }) {Icon(imageVector = Icons.Default.Edit,contentDescription = "Редактировать")}
                    IconButton(onClick = { viewModel.deleteCategory(category) }) {
                        Icon(imageVector = Icons.Default.Delete,contentDescription = "Удалить" )
                    }
                    IconButton(onClick = {
                        val route = Screen.ITEM_SCREEN.route.replace("{categoryId}", category.id.toString())
                        navController.navigate(route)
                    }) {Icon( imageVector = Icons.Default.ShoppingCart,contentDescription = "Товары")}
                }
            }
        }
    }
}