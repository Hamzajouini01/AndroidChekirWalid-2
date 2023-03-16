package com.example.androidchekirwalid.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val bookTitle: String,

    val bookAuthor: String,

    val bookPrice: String,

    val bookLogo: Int
){
    constructor(bookTitle: String, bookAuthor: String, bookPrice: String,
                bookLogo: Int): this(0 ,bookTitle, bookAuthor, bookPrice, bookLogo)
}