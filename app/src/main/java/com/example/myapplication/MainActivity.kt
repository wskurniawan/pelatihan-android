package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.model.database.AccountModel
import com.example.myapplication.repository.AccountRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //cek username yang sudah tersimpan
        val username = getUsername()
        if(username!!.length > 0){
            //set username jika username panjangnya > 0
            editUsername.setText(username)
        }

        //menambahkan click listener
        btnLogin.setOnClickListener{
            getData()
        }

        //menambahkan click listener
        txtRegister.setOnClickListener{
            goToRegister()
        }
    }

    private fun getData(){
        val username = editUsername.text.toString()
        val password = editPassword.text.toString()

        val accountRepository = AccountRepository(this)

        accountRepository.getAccount(username, object: AccountRepository.ResultListener<AccountModel>{
            override fun onResult(data: AccountModel) {
                Log.i("APP", "passworrd: ${data.password} password input: $password" )
                if(data.password.equals(password)){
                    Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_SHORT).show()

                    //jika password benar, simpan username kedalam shared preference
                    saveUsername(username)

                    //buka halaman home
                    startActivity(Intent(applicationContext, Home::class.java))
                    finish()
                }else{
                    Toast.makeText(applicationContext, "password salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    //membuka halaman registerasi
    private fun goToRegister(){
        val intent = Intent(this, Registerasi::class.java)

        startActivity(intent)
        this.finish()
    }

    //menyimpan username login kedalam shared preverence
    private fun saveUsername(username: String){
        val sharedPref = applicationContext.getSharedPreferences("pelatihan_android", Context.MODE_PRIVATE)

        //mendapatkan instance editor untuk shared preverence
        val editor = sharedPref.edit()

        //menyimpan kedalam shared preference
        editor.putString("username", username)
        editor.apply()
    }

    //mendapatkan data dari sharedpreference
    private fun getUsername(): String?{
        val sharedPreferences = applicationContext.getSharedPreferences("pelatihan_android", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")

        return username
    }
}
