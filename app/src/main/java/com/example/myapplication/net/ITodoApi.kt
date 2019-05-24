package com.example.myapplication.net

import com.example.myapplication.model.api.add_todo.AddTodoRequest
import com.example.myapplication.model.api.add_todo.AddTodoResponse
import com.example.myapplication.model.api.get_todo.GetTodoResponse
import com.example.myapplication.model.api.signin.SigninRequest
import com.example.myapplication.model.api.signin.SigninResponse
import com.example.myapplication.model.api.signup.SignupRequest
import com.example.myapplication.model.api.signup.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ITodoApi {
    @POST("/signup")
    fun signup(@Body requestBody: SignupRequest): Call<SignupResponse>

    @POST("/signin")
    fun signin(@Body requestBody: SigninRequest): Call<SigninResponse>

    @POST("/add-todo")
    fun addTodo(@Body requestBody: AddTodoRequest, @Header("token") token: String): Call<AddTodoResponse>

    @GET("/get-todo")
    fun getTodo(@Header("token") token: String): Call<GetTodoResponse>
}