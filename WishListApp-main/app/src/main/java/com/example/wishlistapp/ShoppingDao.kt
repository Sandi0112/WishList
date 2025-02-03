package com.example.wishlistapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Insert suspend fun insertCategory(category: Category)
    @Delete suspend fun deleteCategory(category: Category)
    @Update suspend fun updateCategory(category: Category)

    @Insert suspend fun insertItem(item: Item)
    @Delete suspend fun deleteItem(item: Item)
    @Update suspend fun updateItem(item: Item)

    @Query("SELECT * FROM items WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Int): Flow<List<Item>>

    @Query("SELECT * FROM categories WHERE id = :categoryId LIMIT 1")
    fun getCategoryById(categoryId: Int): Flow<Category?>

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    fun getItemById(itemId: Int): Flow<Item?>

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>
}
