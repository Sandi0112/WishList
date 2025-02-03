package com.example.wishlistapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Item::class],version = 1,exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {abstract fun shoppingDao(): ShoppingDao }