package com.progos.sqliteexample

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout


class ViewUser : AppCompatActivity() {
//    var userDbHelper: DbHelper? = null
//    var sqLiteDatabase: SQLiteDatabase? = null
    var cursor: Cursor? = null
    val Context.database: DbHelper
        get() = DbHelper.getInstance(getApplicationContext())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user)
        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

//        userDbHelper = DbHelper(this)
//        sqLiteDatabase = userDbHelper?.readableDatabase
        val db = database.readableDatabase
//        cursor  = userDbHelper?.getInformation(sqLiteDatabase!!)
            cursor  = database.getInformation(db)
        val items = ArrayList<User>()
        if (cursor!!.moveToFirst()){
            do{
                val name: String = cursor!!.getString(0)
                val mobile: String = cursor!!.getString(1)
                val email: String = cursor!!.getString(2)
                items.add(User(name,mobile,email))
                val adapter = ViewAdapter(items)
                mRecyclerView.adapter = adapter
            }while (cursor!!.moveToNext())
        }
        cursor?.close()
//        userDbHelper?.close()
//        sqLiteDatabase?.close()
    }
}
