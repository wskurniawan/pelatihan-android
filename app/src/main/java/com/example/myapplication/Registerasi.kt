package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_registerasi.*

class Registerasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerasi)

        btnSignup.setOnClickListener(View.OnClickListener {
            openHome()
        })
    }

    private fun openHome(){
        intent = Intent(this, Home::class.java)

        startActivity(intent)
        this.finish()
    }
}
