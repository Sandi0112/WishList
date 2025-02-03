package com.example.wishlistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> get() = _categories

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> get() = _items

    fun getCategoryById(categoryId: Int): Flow<Category?> {return repository.getCategoryById(categoryId) }
    fun getAllCategories() { viewModelScope.launch {repository.getAllCategories().collect { _categories.value = it } } }
    fun addCategory(category: Category) {viewModelScope.launch {repository.insertCategory(category) } }
    fun updateCategory(category: Category) {viewModelScope.launch {repository.updateCategory(category) } }
    fun deleteCategory(category: Category) { viewModelScope.launch {repository.deleteCategory(category) } }

    fun addItem(item: Item) {viewModelScope.launch {repository.insertItem(item) }}
    fun updateItem(item: Item) {viewModelScope.launch {repository.updateItem(item) } }
    fun deleteItem(item: Item) {viewModelScope.launch {repository.deleteItem(item)} }

    fun getItemsByCategory(categoryId: Int) {
        viewModelScope.launch {
            repository.getItemsByCategory(categoryId).collect { items ->
                _items.value = items
            }
        }
    }
    fun getItemById(itemId: Int): Flow<Item?> { return repository.getItemById(itemId)}
}