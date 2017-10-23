package com.progos.sqliteexample

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout


class ViewUser : AppCompatActivity() {
    var userDbHelper: DbHelper? = null
    var sqLiteDatabase: SQLiteDatabase? = null
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user)
        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        userDbHelper = DbHelper(this)
        sqLiteDatabase = userDbHelper?.readableDatabase
        cursor  = userDbHelper?.getInformation(sqLiteDatabase!!)
        if (cursor!!.moveToFirst()){
            do{
                var name: String = cursor!!.getString(0)
                var mobile: String = cursor!!.getString(1)
                var email: String = cursor!!.getString(2)
                val items = ArrayList<User>()
                items.add(User(name,mobile,email))
                val adapter = ViewAdapter(items)
                mRecyclerView.adapter = adapter
            }while (cursor!!.moveToNext())
        }
        cursor?.close()
        userDbHelper?.close()
        sqLiteDatabase?.close()
    }
}
