package com.progos.sqliteexample

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DeleteUser : AppCompatActivity() {

    val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_user)
        val name: EditText = findViewById<EditText>(R.id.delete_text)
        val delete: Button = findViewById<Button>(R.id.delete)
        delete.setOnClickListener {
            val db = database.readableDatabase
            val name_s = name.text.toString()
            try {
                database.deleteInformation(name_s, db)
                Toast.makeText(baseContext, "The user name deleted Successfully", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteException) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Exception Found", "Record not deleted")
                e.printStackTrace()
            } finally {
                name.text.clear()
            }
            val intent = Intent(this@DeleteUser, MainActivity::class.java)
            startActivity(intent)
            Log.d("Launch Activity", "Main Activity")
        }
    }
}
