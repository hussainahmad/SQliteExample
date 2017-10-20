package com.progos.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.progos.sqliteexample.DbHelper.Companion.Table_USER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by Hussain Sherwani
 * hussain.ahmed@progos.org
 * on 10/20/2017.
 */
class DbHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "testing_db.db", null, 1)  {

    companion object {
        val Table_USER: String = "TABLE_USER"
        val ID:String = "ID_"
        val NAME: String = "NAME"
        val PASSWORD:String = "PASSWORD"
        val EMAIL: String = "EMAIL"
    }

    //Creating Table
    val CREATE_TABLE = "CREATE TABLE if not exist " + Table_USER + "(" +
            "$ID integer PRIMARY KEY autoincrement," +
            "$NAME text," +
            "$PASSWORD text," +
            "$EMAIL text" +
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(name:String, password: String, email: String): Long {
        val values = ContentValues()
        values.put(NAME, name)
        values.put(PASSWORD,password)
        values.put(EMAIL,email)
        return writableDatabase.insert(Table_USER,null,values)
    }

    //Getting all students list
    fun getAllStudentData(): MutableList<Student> {
        val stuList: MutableList<Student> = mutableListOf<Student>()
        val cursor: Cursor = getReadableDatabase().query(Table_USER, arrayOf(ID, NAME, PASSWORD, EMAIL), null, null, null, null, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val name: String = cursor.getString(cursor.getColumnIndex(NAME))
                        val password: String = cursor.getString(cursor.getColumnIndex(PASSWORD))
                        val email: String = cursor.getString(cursor.getColumnIndex(EMAIL))
                        stuList.add(Student(name,password, email))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return stuList
    }

    //Getting student/students data using where clause
    fun getParticularStudentData(name: String): MutableList<Student> {
        val stuList: MutableList<Student> = mutableListOf<Student>()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + Table_USER + " WHERE " + NAME + " = '" + name + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        val name: String = cursor.getString(cursor.getColumnIndex(NAME))
                        val password: String = cursor.getString(cursor.getColumnIndex(PASSWORD))
                        val email: String = cursor.getString(cursor.getColumnIndex(EMAIL))
                        stuList.add(Student(name, password, email))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }

        return stuList
    }
}