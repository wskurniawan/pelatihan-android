package com.example.myapplication.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.myapplication.model.database.TodoItemModel

@Dao
interface TodoItemDao {
    @Insert
    fun inserTodoItem(todoItem: TodoItemModel)

    @Query("SELECT * FROM TodoItemModel WHERE username = :username")
    fun getTodoItem(username: String?): List<TodoItemModel>

    @Query("DELETE FROM TodoItemModel WHERE id = :id")
    fun deleteTodoItem(id: Int)
}