package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.model.database.AccountModel
import com.example.myapplication.repository.AccountRepository
import kotlinx.android.synthetic.main.activity_registerasi.*

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

        val dataAccountModel = AccountModel()
        dataAccountModel.password = password
        dataAccountModel.username = username

        val accountRepository = AccountRepository(this)
        accountRepository.insertAccount(dataAccountModel)

        Toast.makeText(this, "Data account berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
