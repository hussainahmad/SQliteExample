package com.progos.sqliteexample

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class SearchUser : AppCompatActivity() {

    val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        val search_text:EditText = findViewById<EditText>(R.id.editText)
//        val name: TextView = findViewById<TextView>(R.id.name)
//        val password: TextView = findViewById<TextView>(R.id.password)
//        val email: TextView = findViewById<TextView>(R.id.email)
        val search: Button = findViewById<Button>(R.id.button)
        val mRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        search.setOnClickListener {
            val name1 = search_text.text.toString()
            search_text.text.clear()
            val db = database.readableDatabase
            cursor = database.getContact(name1,db)
            val items = ArrayList<User>()
            if (cursor!!.moveToFirst()){
                do{
                    val name_s: String = cursor!!.getString(0)
                    val password_s: String = cursor!!.getString(1)
                    val email_s: String = cursor!!.getString(2)
//                    name.text = name_s
//                    password.text = password_s
//                    email.text = email_s
                    items.add(User(name_s,password_s,email_s))
                    val adapter = ViewAdapter(items)
                    mRecyclerView.adapter = adapter
                } while (cursor!!.moveToNext())
            }
            cursor!!.close()
        }
    }
}
