package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.myapplication.helper.UserData
import com.example.myapplication.model.api.add_todo.AddTodoRequest
import com.example.myapplication.model.api.add_todo.AddTodoResponse
import com.example.myapplication.model.database.TodoItemModel
import com.example.myapplication.net.RetrofitClient
import com.example.myapplication.repository.TodoItemRepository
import kotlinx.android.synthetic.main.activity_add_todo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AddTodo : AppCompatActivity() {
    private lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        selectedDate = Calendar.getInstance()

        btnSetDate.setOnClickListener {
            setDate()
        }

        btnSetTime.setOnClickListener {
            setTime()
        }

        btnSaveTodo.setOnClickListener {
            saveTodoItem()
        }
    }

    private  fun setDate(){
        val calendar = Calendar.getInstance()

        val datepickerDialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                selectedDate.set(year, month, dayOfMonth)

                updateSelectedTime()
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datepickerDialog.show()
    }

    private fun setTime(){
        val timePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedDate.set(Calendar.MINUTE, minute)

                updateSelectedTime()
            }
        }, selectedDate.get(Calendar.HOUR_OF_DAY), selectedDate.get(Calendar.MINUTE), true)

        timePicker.show()
    }

    private fun updateSelectedTime(){
        val text = "${selectedDate.get(Calendar.DAY_OF_MONTH)} - ${selectedDate.get(Calendar.MONTH) + 1} - ${selectedDate.get(Calendar.YEAR)}, ${selectedDate.get(Calendar.HOUR_OF_DAY)}:${selectedDate.get(Calendar.MINUTE)}"

        txtSelectedDatetime.text = text
    }

    private fun saveTodoItem(){
        val requestBody = AddTodoRequest(namaKegiatan = editNamaKegiatan.text.toString(), timestamp = selectedDate.timeInMillis)
        val apiClient = RetrofitClient().getClient()

        apiClient.addTodo(requestBody, UserData.token).enqueue(object: Callback<AddTodoResponse>{
            override fun onResponse(call: Call<AddTodoResponse>, response: Response<AddTodoResponse>) {
                if(response.body() != null){
                    Toast.makeText(applicationContext, "Todo item ditambahkan", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Tidak dapat menambahkan todo list", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddTodoResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}
