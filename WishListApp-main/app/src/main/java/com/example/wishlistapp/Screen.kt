package com.example.wishlistapp

enum class Screen(val route: String) {
    MAIN_SCREEN("main_screen"),
    CATEGORY_SCREEN_NEW("category_screen/new"),
    EDIT_CATEGORY_SCREEN("edit_category_screen/{categoryId}"),
    ITEM_SCREEN("item_screen/{categoryId}"),
    ADD_ITEM_SCREEN("add_item_screen/{categoryId}"),
    EDIT_ITEM_SCREEN("edit_item_screen/{itemId}")
}