package com.example.wishlistapp


import kotlinx.coroutines.flow.Flow

class ShoppingRepository(private val shoppingDao: ShoppingDao) {
    fun getItemsByCategory(categoryId: Int): Flow<List<Item>> {return shoppingDao.getItemsByCategory(categoryId) }
    fun getCategoryById(categoryId: Int): Flow<Category?> {return shoppingDao.getCategoryById(categoryId) }
    fun getItemById(itemId: Int): Flow<Item?> {return shoppingDao.getItemById(itemId) }
    fun getAllCategories(): Flow<List<Category>> = shoppingDao.getAllCategories()

    suspend fun insertCategory(category: Category) = shoppingDao.insertCategory(category)
    suspend fun deleteCategory(category: Category) = shoppingDao.deleteCategory(category)
    suspend fun updateCategory(category: Category) = shoppingDao.updateCategory(category)

    suspend fun insertItem(item: Item) = shoppingDao.insertItem(item)
    suspend fun deleteItem(item: Item) = shoppingDao.deleteItem(item)
    suspend fun updateItem(item: Item) = shoppingDao.updateItem(item)


}