package com.example.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.database.TodoItemModel
import kotlinx.android.synthetic.main.item_todo.view.*
import java.util.*

class TodoListAdapter(private val listTodo: List<TodoItemModel>) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val namaKegiatan = view.txtNamaKegiatan
        val waktuKegiatan = view.txtTanggalKegiatan
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false) as View

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = listTodo.get(position)
        val date = Calendar.getInstance()
        date.timeInMillis = todoItem.timestamp as Long
        val txtDate = "${date.get(Calendar.DAY_OF_MONTH)} - ${date.get(Calendar.MONTH) + 1} - ${date.get(Calendar.YEAR)}, ${date.get(Calendar.HOUR_OF_DAY)}:${date.get(Calendar.MINUTE)}"

        holder.namaKegiatan.text = todoItem.namaKegiatan
        holder.waktuKegiatan.text = txtDate

    }

    override fun getItemCount(): Int {
        return listTodo.size
    }
}