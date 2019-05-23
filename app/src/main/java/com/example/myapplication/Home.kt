package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.myapplication.adapter.TodoListAdapter
import com.example.myapplication.helper.UserData
import com.example.myapplication.model.database.TodoItemModel
import com.example.myapplication.repository.TodoItemRepository
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initTodoList()

        btnOpenAddTodo.setOnClickListener {
            openAddTodo()
        }
    }

    private fun openAddTodo(){
        val intent = Intent(this, AddTodo::class.java)
        startActivity(intent)
    }

    private fun initTodoList(){
        val todoItemRepository = TodoItemRepository(this)
        val username = UserData.username
        Log.i("APP", "Username: $username")
        todoItemRepository.getTodoList(username, object: TodoItemRepository.ResultListener<List<TodoItemModel>>{
            override fun onResult(data: List<TodoItemModel>) {
                renderTodoList(data)
            }
        })
    }

    private fun renderTodoList(listData: List<TodoItemModel>){
        Log.i("APP", "Disini")
        Log.i("APP", listData.toString())
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = TodoListAdapter(listData)
        val recyclerView = findViewById<RecyclerView>(R.id.listTodoList).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
