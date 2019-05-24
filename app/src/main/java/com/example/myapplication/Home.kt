package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.myapplication.adapter.TodoListAdapter
import com.example.myapplication.helper.UserData
import com.example.myapplication.model.api.get_todo.GetTodoResponse
import com.example.myapplication.model.database.TodoItemModel
import com.example.myapplication.net.RetrofitClient
import com.example.myapplication.repository.TodoItemRepository
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getTodoList()

        btnOpenAddTodo.setOnClickListener {
            openAddTodo()
        }
    }

    override fun onResume() {
        super.onResume()

        getTodoList()
    }

    private fun getTodoList(){
        val apiClient = RetrofitClient().getClient()

        apiClient.getTodo(UserData.token).enqueue(object: Callback<GetTodoResponse>{
            override fun onResponse(call: Call<GetTodoResponse>, response: Response<GetTodoResponse>) {
                if(response.body() != null){
                    handleTodoResponse(response.body()!!)
                }else{
                    Toast.makeText(applicationContext, "Tidak dapat memuat todo list", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetTodoResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun handleTodoResponse(response: GetTodoResponse){
        if(response.success!!){
            val todoResponseList = response.data!!.listTodo!!
            val todoModelList: MutableList<TodoItemModel> = mutableListOf()

            for((index, item) in todoResponseList.withIndex()){
                val todoItem = TodoItemModel()
                todoItem.id = index
                todoItem.namaKegiatan = item!!.namaKegiatan
                todoItem.username = item.username
                todoItem.timestamp = item.timestamp

                todoModelList.add(todoItem)
            }

            renderTodoList(todoModelList)
        }else{
            Toast.makeText(this, response.error!!.message!!, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openAddTodo(){
        val intent = Intent(this, AddTodo::class.java)
        startActivity(intent)
    }

    private fun renderTodoList(listData: List<TodoItemModel>){
        Log.i("APP", "Disini")
        Log.i("APP", listData.toString())
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = TodoListAdapter(listData)
        findViewById<RecyclerView>(R.id.listTodoList).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
