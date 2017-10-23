package com.progos.sqliteexample

/**
 * Created by Hussain Sherwani
 * hussain.ahmed@progos.org
 * on 10/23/2017.
 */
data class User(val name: String, val password: String, val email: String) {

    companion object {
        val Table_USER: String = "TABLE_USER"
        val ID: String = "ID_"
        val NAME: String = "NAME"
        val PASSWORD: String = "PASSWORD"
        val EMAIL: String = "EMAIL"
    }
}