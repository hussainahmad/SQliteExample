package com.progos.sqliteexample

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SignUp : AppCompatActivity() {

    val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
//        userDbHelper = DbHelper(this)
        val name: EditText = findViewById<EditText>(R.id.user_name)
        val password: EditText = findViewById<EditText>(R.id.password)
        val email: EditText = findViewById<EditText>(R.id.email)
        val save = findViewById<Button>(R.id.add_user_save)

        save.setOnClickListener {
            val name1 = name.text.toString()
            val mPassword = password.text.toString()
            val email1 = email.text.toString()
            try {
                val db = database.writableDatabase
                database.addInformation(name1, mPassword, email1, db)
                Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_LONG).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Exception Found", "Record not save")
                e.printStackTrace()
            } finally {
                name.text.clear()
                password.text.clear()
                email.text.clear()
            }
            val intent = Intent(this@SignUp, MainActivity::class.java)
            startActivity(intent)
            Log.d("Launch Activity", "Main Activity")
        }

    }
}
