package com.progos.sqliteexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addUser = findViewById<Button>(R.id.add_user)
        val viewUser= findViewById<Button>(R.id.view_user)
        val searchUser= findViewById<Button>(R.id.search_user)
        val deleteUser= findViewById<Button>(R.id.delete_user)

        addUser.setOnClickListener{
            val intant = Intent(this@MainActivity, SignUp::class.java)
            startActivity(intant)
        }
        viewUser.setOnClickListener{
            val intant = Intent(this@MainActivity, ViewUser::class.java)
            startActivity(intant)
        }
        searchUser.setOnClickListener{
            val intant = Intent(this@MainActivity, SearchUser::class.java)
            startActivity(intant)
        }
        deleteUser.setOnClickListener{
            val intant = Intent(this@MainActivity, DeleteUser::class.java)
            startActivity(intant)
        }
    }

}
