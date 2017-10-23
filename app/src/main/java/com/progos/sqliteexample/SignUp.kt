package com.progos.sqliteexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SignUp : AppCompatActivity() {

//    var userDbHelper: DbHelper? = null
//    var sqLiteDatabase: SQLiteDatabase? = null
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
            name.text.clear()
            val mPassword = password.text.toString()
            password.text.clear()
            val email1 = email.text.toString()
            email.text.clear()
            val db = database.writableDatabase;
//            sqLiteDatabase = userDbHelper?.writableDatabase
//            userDbHelper?.addInformation(name1, mPassword, email1, db!!)
            database.addInformation(name1,mPassword,email1,db)
            Toast.makeText(baseContext, "Data Saved", Toast.LENGTH_LONG).show()
//            userDbHelper?.close()
//            sqLiteDatabase?.close()
            val intent = Intent(this@SignUp, MainActivity::class.java)
            startActivity(intent)
            Log.d("Launch Activity", "Main Activity")
        }

    }
//    fun insertData(){
//        val name1 = name.text.toString()
//        name.text.clear()
//        val mPassword = password.text.toString()
//        password.text.clear()
//        val email1 = email.text.toString()
//        email.text.clear()
//        database.addInformation()
//    }
}
