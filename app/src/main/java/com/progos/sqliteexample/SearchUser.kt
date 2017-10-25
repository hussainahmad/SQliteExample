package com.progos.sqliteexample

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class SearchUser : AppCompatActivity() {

    private val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())
    private var cursor: Cursor? = null

    private lateinit var search_text: EditText
    private lateinit var search: Button
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        search_text = findViewById<EditText>(R.id.editText)
//        val name: TextView = findViewById<TextView>(R.id.name)
//        val password: TextView = findViewById<TextView>(R.id.password)
//        val email: TextView = findViewById<TextView>(R.id.email)
        search = findViewById<Button>(R.id.button)
        mRecyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        search.setOnClickListener {
            val name1 = search_text.text.toString()
            try {
                val db = database.readableDatabase
                cursor = database.getContact(name1, db)
                val items = ArrayList<User>()
                if (cursor!!.moveToFirst()) {
                    do {
//                    val name_s: String = cursor!!.getString(0)
                        val password_s: String = cursor!!.getString(0)
                        val email_s: String = cursor!!.getString(1)
//                    name.text = name_s
//                    password.text = password_s
//                    email.text = email_s
                        items.add(User(name1, password_s, email_s))
                        val adapter = ViewAdapter(items)
                        mRecyclerView.adapter = adapter
                    } while (cursor!!.moveToNext())
                }
                cursor!!.close()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Exception Found", "Record not found")
                e.printStackTrace()
            } finally {
                search_text.text.clear()
            }
        }

    }
}
