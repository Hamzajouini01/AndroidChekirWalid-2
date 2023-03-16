package com.example.androidchekirwalid.dao

import androidx.room.*
import com.example.androidchekirwalid.data.Book

@Dao
interface BookDao {
    @Insert
    fun insert(c: Book)
    @Update
    fun update(c: Book)

    @Delete
    fun delete(c: Book)

    @Query("DELETE FROM book")
    fun deleteAll()

    @Query("SELECT * FROM book")
    fun getAllCars(): MutableList<Book>

    @Query("SELECT * FROM book WHERE bookTitle LIKE :search")
    fun findCar(search: String?): Book
}