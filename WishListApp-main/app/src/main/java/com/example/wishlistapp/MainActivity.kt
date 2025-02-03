package com.example.wishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    val db = remember { Room.databaseBuilder( applicationContext, ShoppingDatabase::class.java,"shopping_database").build()}
                    val repository = remember { ShoppingRepository(db.shoppingDao()) }
                    val viewModel = remember { ShoppingViewModel(repository) }

                    NavHost(navController = navController,startDestination = Screen.MAIN_SCREEN.route) {
                        composable(Screen.MAIN_SCREEN.route) {MainScreen(viewModel, navController)}
                        composable(Screen.CATEGORY_SCREEN_NEW.route) {CategoryScreen(viewModel, navController)}
                        composable(Screen.EDIT_CATEGORY_SCREEN.route,arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val categoryId = backStackEntry.arguments?.getInt("categoryId")
                            if (categoryId != null) {EditCategoryScreen(viewModel, navController, categoryId) }
                        }
                        composable(Screen.ITEM_SCREEN.route,arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val categoryId = backStackEntry.arguments?.getInt("categoryId")
                            if (categoryId != null) {ItemScreen(viewModel, navController, categoryId)}
                        }
                        composable(Screen.ADD_ITEM_SCREEN.route,arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val categoryId = backStackEntry.arguments?.getInt("categoryId")
                            if (categoryId != null) {AddItemScreen(viewModel, navController, categoryId) }
                        }
                        composable(Screen.EDIT_ITEM_SCREEN.route,arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val itemId = backStackEntry.arguments?.getInt("itemId")
                            if (itemId != null) {EditItemScreen(viewModel, navController, itemId)}
                        }
                    }
                }
            }
        }
    }
}