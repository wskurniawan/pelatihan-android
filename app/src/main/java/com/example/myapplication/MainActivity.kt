package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.helper.UserData
import com.example.myapplication.model.api.signin.SigninRequest
import com.example.myapplication.model.api.signin.SigninResponse
import com.example.myapplication.model.database.AccountModel
import com.example.myapplication.net.RetrofitClient
import com.example.myapplication.repository.AccountRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            doLogin()
        }

        //menambahkan click listener
        txtRegister.setOnClickListener{
            goToRegister()
        }
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

    private fun doLogin(){
        val username = editUsername.text.toString()
        val password = editPassword.text.toString()

        val apiService = RetrofitClient().getClient()
        val requestBody = SigninRequest(password, username)

        //simpan username kedalam shared preference
        saveUsername(username)
        UserData.username = username

        apiService.signin(requestBody).enqueue(object: Callback<SigninResponse>{
            override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                if(response.body() != null){
                    handleSigninResponse(response.body()!!)
                }else{
                    Toast.makeText(applicationContext, "Tidak dapat login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleSigninResponse(response: SigninResponse){
        if(response.success!!){
            UserData.token = response.data!!.token!!

            startActivity(Intent(applicationContext, Home::class.java))
        }else{
            Toast.makeText(applicationContext, response.error!!.message!!, Toast.LENGTH_SHORT).show()
        }
    }
}
