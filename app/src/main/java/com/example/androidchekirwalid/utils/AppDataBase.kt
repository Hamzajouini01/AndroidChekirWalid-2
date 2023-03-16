package com.example.androidchekirwalid.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidchekirwalid.dao.BookDao
import com.example.androidchekirwalid.data.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "AndroidCvDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance!!
        }
    }
}