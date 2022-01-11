package com.example.navigationview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.navigationview.models.User
import com.example.navigationview.services.Fakeapirest
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getSupportActionBar()?.hide()
        initUI()
    }

    fun goActivityHome(user: User){
        val intent = Intent(this,HomeActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }

    fun initUI(){
        val btnContinue = findViewById<Button>(R.id.iniciarBtn)
        val userName = findViewById<TextInputEditText>(R.id.user_name)
        val userPassword = findViewById<TextInputEditText>(R.id.user_password)
        btnContinue.setOnClickListener { getUserList(userName.text.toString(), userPassword.text.toString()) }
    }

    fun getUserList(userName: String, password: String) {
        val retrofit = Retrofit.Builder().
            baseUrl("https://my-json-server.typicode.com/neoshock/fakeonlinerestserver/").
            addConverterFactory(GsonConverterFactory.create()).build()
        val fakeapirest = retrofit.create(Fakeapirest::class.java)

        val call: Call<List<User>> = fakeapirest.getUsers()

        call.enqueue(object :Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                println("###Error###" + t.message)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                var userList: List<User> = response.body()!!
                for (user in userList){
                    if(user.name == userName && user.password == password){
                        goActivityHome(user)
                        break
                    }else{
                        //Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}