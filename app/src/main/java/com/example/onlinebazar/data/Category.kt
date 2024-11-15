package com.example.onlinebazar.data

sealed class Category(val category: String) {

    object Chair: Category("Statue")
    object Cupboard: Category("Singing Bowls")
    object Table: Category("Thanka Painting")
    object Accessory: Category("Beads Mala")
    object Furniture: Category("Hemp Products")
}