package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_add_todo.*
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
}
