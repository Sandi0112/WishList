package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EditCategoryScreen(viewModel: ShoppingViewModel, navController: NavController, categoryId: Int) {
    val categoryName = remember { mutableStateOf("") }
    val category by viewModel.getCategoryById(categoryId).collectAsState(initial = null)

    LaunchedEffect(category) {  category?.let {categoryName.value = it.name} }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Редактирование категории", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = categoryName.value,
            onValueChange = { categoryName.value = it },
            label = { Text("Название категории") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row( modifier = Modifier.fillMaxWidth().padding(8.dp),horizontalArrangement = Arrangement.spacedBy(16.dp) ) {
            Button(
                onClick = {
                    viewModel.updateCategory(category = Category(id = categoryId, name = categoryName.value))
                    navController.popBackStack()
                }
            ) {Text("Обновить")}
            Button(onClick = { navController.popBackStack() }) {Text("Отмена")}
        }
    }
}
