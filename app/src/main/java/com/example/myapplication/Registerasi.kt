package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.model.api.signup.SignupRequest
import com.example.myapplication.model.api.signup.SignupResponse
import com.example.myapplication.model.database.AccountModel
import com.example.myapplication.net.RetrofitClient
import com.example.myapplication.repository.AccountRepository
import kotlinx.android.synthetic.main.activity_registerasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registerasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerasi)

        btnSignup.setOnClickListener{
            register()
        }
    }

    private fun register(){
        val username = editUsername.text.toString()
        val password = editPassword.text.toString()

        val signupRequest = SignupRequest(password, username)
        val apiClient = RetrofitClient().getClient()

        apiClient.signup(signupRequest).enqueue(object: Callback<SignupResponse>{
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                Log.i("APP", "Disini");
                if(response.body() != null){
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Tidak dapat dignup", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
