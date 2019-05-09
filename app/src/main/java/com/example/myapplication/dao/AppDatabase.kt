package com.example.myapplication.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.myapplication.model.database.AccountModel
import com.example.myapplication.model.database.TodoItemModel

@Database(entities = [AccountModel::class, TodoItemModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun todoItemDao(): TodoItemDao

    companion object {
        private var dbInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase{
            if(dbInstance == null){
                dbInstance = Room.databaseBuilder(context, AppDatabase::class.java, "pelatihan_android").fallbackToDestructiveMigration().build()
            }

            return dbInstance!!
        }
    }
}