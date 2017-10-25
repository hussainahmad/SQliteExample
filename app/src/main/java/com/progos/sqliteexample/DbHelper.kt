package com.progos.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*

/**
 * Created by Hussain Sherwani
 * hussain.ahmed@progos.org
 * on 10/20/2017.
 */
class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DbHelper.DB_NAME, null, DbHelper.DB_VERSION) {

    //    companion object {
//        val Table_USER: String = "TABLE_USER"
//        val ID:String = "ID_"
//        val NAME: String = "NAME"
//        val PASSWORD:String = "PASSWORD"
//        val EMAIL: String = "EMAIL"
//    }
//    Kotlin does not use Java concept of static,  because kotlin has its own concept of object
//    As Java static part of a class can be elegantly expressed in terms of singleton: it's a singleton
//    object that can be called , Apart from naming, it is more powerful than Java static members:
//    it can extend classes and interfaces, and you can pass it around as any other objects.
    companion object {
        val DB_NAME = "user.db"
        val DB_VERSION = 1
        private var instance: DbHelper? = null
        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

//    //Creating Table
//    val CREATE_TABLE = "CREATE TABLE if not exists " + Table_USER + "(" +
//            "$ID integer PRIMARY KEY autoincrement," +
//            "$NAME text," +
//            "$PASSWORD text," +
//            "$EMAIL text" +
//            ")"


    //    As we know Kotlin is null safe language , to handel this Kotlin defined "?"
//    to define nullable type we declare variable with ?
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.createTable(User.Table_USER, true,
                User.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                //                User.ID to INTEGER + AUTOINCREMENT,
                User.NAME to TEXT + UNIQUE,
                User.PASSWORD to TEXT,
                User.EMAIL to TEXT)
//        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        As db is nullable type to initialize we add !! which means if db not equals to null
        db!!.dropTable(User.Table_USER, true)
        onCreate(db)
    }

//     Access property for Context
//    val Context.database: DbHelper
//        get() = DbHelper.getInstance(getApplicationContext())

    fun addInformation(name: String, password: String, email: String, db: SQLiteDatabase) {

        db.insert(User.Table_USER, User.NAME to name, User.PASSWORD to password,
                User.EMAIL to email)
        // we can also use through Content values but here I am using upper defined method, but If you want through content valued it works perfectly
//        val values = ContentValues()
//        values.put(User.NAME, name)
//        values.put(User.PASSWORD, password)
//        values.put(User.EMAIL, email)
//
//        db.insert(User.Table_USER, null, values)
//        Log.i("DATABASE OPERATION", "One Row data inserted")

    }

    fun getInformation(db: SQLiteDatabase): Cursor {

        val cursor: Cursor
        val projection = arrayOf<String>(User.NAME, User.PASSWORD, User.EMAIL)
        cursor = db.query(User.Table_USER, projection, null, null, null, null, null)
        return cursor
    }

    fun getContact(username: String, db: SQLiteDatabase): Cursor {

        val projection = arrayOf<String>(User.PASSWORD, User.EMAIL)
        val selection = User.NAME + "=?"
        val selectionArguments = arrayOf(username)
        return db.query(User.Table_USER, projection, selection, selectionArguments, null, null, null)

    }

    fun deleteInformation(username: String, db: SQLiteDatabase) {
        val selection = User.NAME + "= ?"
        val selectionArguments = arrayOf(username)
        db.delete(User.Table_USER, selection, selectionArguments)
        Log.i("DataBase Operation", "Selected Row deleted from Database")
    }

    fun updateInformation(username: String, updated_name: String, mobile: String, email: String, db: SQLiteDatabase) {

        val selection = User.NAME + "=?"
        val selectionArguments = arrayOf(username)
        // New value for one column
        val values = ContentValues()
        values.put(User.NAME, updated_name)
        values.put(User.PASSWORD, mobile)
        values.put(User.EMAIL, email)
        db.update(User.Table_USER, values, selection, selectionArguments)
        Log.i("DataBase Operation", "Selected Row Updated from Database")
    }
}
