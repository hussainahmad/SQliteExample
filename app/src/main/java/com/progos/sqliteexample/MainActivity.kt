package com.progos.sqliteexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addUser: Button = findViewById<Button>(R.id.add_user)
        val viewUser: Button = findViewById<Button>(R.id.view_user)
        val searchUser: Button = findViewById<Button>(R.id.search_user)
        val deleteUser: Button = findViewById<Button>(R.id.delete_user)
        val updateUser: Button = findViewById<Button>(R.id.update)

        addUser.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUp::class.java)
            startActivity(intent)
        }
        viewUser.setOnClickListener {
            val intent = Intent(this@MainActivity, ViewUser::class.java)
            startActivity(intent)
        }
        searchUser.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchUser::class.java)
            startActivity(intent)
        }
        deleteUser.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteUser::class.java)
            startActivity(intent)
        }
        updateUser.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
        }
    }

}
