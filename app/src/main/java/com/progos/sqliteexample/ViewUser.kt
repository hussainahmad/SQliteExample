package com.progos.sqliteexample

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout


class ViewUser : AppCompatActivity() {

    private var cursor: Cursor? = null
    private val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val db = database.readableDatabase
        cursor = database.getInformation(db)
        val items = ArrayList<User>()
        if (cursor!!.moveToFirst()) {
            do {
                val name: String = cursor!!.getString(0)
                val mobile: String = cursor!!.getString(1)
                val email: String = cursor!!.getString(2)
                items.add(User(name, mobile, email))
                val adapter = ViewAdapter(items)
                recyclerView.adapter = adapter
            } while (cursor!!.moveToNext())
        }
        cursor?.close()
    }
}
