package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener{
            getData()
        }

        txtRegister.setOnClickListener{
            goToRegister()
        }
    }

    private fun getData(){
        val username = editUsername.text.toString()
        var password = editPassword.text.toString()

        Toast.makeText(this, "Username: $username Password: $password", Toast.LENGTH_SHORT).show()
    }

    private fun goToRegister(){
        val intent = Intent(this, Registerasi::class.java)

        startActivity(intent)
        this.finish()
    }
}
