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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddItemScreen(viewModel: ShoppingViewModel, navController: NavController, categoryId: Int) {
    val itemName = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Добавить товар", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = itemName.value,
            onValueChange = { itemName.value = it },
            label = { Text("Название товара") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    val newItem = Item(name = itemName.value, categoryId = categoryId)
                    viewModel.addItem(newItem)
                    navController.popBackStack()
                }
            ) {Text("Добавить")}
            Button(onClick = { navController.popBackStack() }) {Text("Отмена") }
        }

    }
}