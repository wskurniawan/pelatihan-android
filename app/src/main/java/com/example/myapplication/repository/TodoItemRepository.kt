package com.example.myapplication.repository

import android.content.Context
import android.util.Log
import com.example.myapplication.dao.AppDatabase
import com.example.myapplication.model.database.TodoItemModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

class TodoItemRepository(context: Context){
    val appDatabase = AppDatabase.getInstance(context)

    interface ResultListener<ResultType>{
        fun onResult(data: ResultType)
    }

    fun insertTodoItem(data: TodoItemModel){
        doAsync {
            appDatabase.todoItemDao().inserTodoItem(data)
        }
    }

    fun getTodoList(username: String?, resultListener: ResultListener<List<TodoItemModel>>){
        Log.i("APP", username)
        doAsync {
            val listTodoItem = appDatabase.todoItemDao().getTodoItem(username)

            onComplete {
                resultListener.onResult(listTodoItem)
            }
        }
    }

    fun deleteTodoItem(id: Int){
        doAsync {
            appDatabase.todoItemDao().deleteTodoItem(id)
        }
    }
}