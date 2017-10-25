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

class UpdateActivity : AppCompatActivity() {
    private val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())

    private lateinit var name: EditText
    private lateinit var delete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        name = findViewById<EditText>(R.id.update_text)
        delete = findViewById<Button>(R.id.update_button)
        delete.setOnClickListener {
            val db = database.readableDatabase
            val name_s = name.text.toString()
            // here I am updating contacts statically but U can do any any thing as you want,
            // I am Just exploring database functionality in kotlin
            // hopes you love this
            try {
                database.updateInformation(name_s, "Hussain Sherwani", "Admin", "hussain.ahmad5539@gmail.com", db)
                Toast.makeText(baseContext, "updated Successfully", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Exception Found", "Record not updated")
                e.printStackTrace()
            } finally {
                name.text.clear()
            }
            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
            startActivity(intent)
            Log.d("Launch Activity", "Main Activity")
        }
    }
}
