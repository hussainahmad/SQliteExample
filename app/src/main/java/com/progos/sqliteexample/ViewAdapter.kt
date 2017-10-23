package com.progos.sqliteexample

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Hussain Sherwani
 * hussain.ahmed@progos.org
 * on 10/23/2017.
 */
class ViewAdapter(val list:ArrayList<User>): RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : User){
            val name: TextView = itemView.findViewById(R.id.name)
            val password: TextView = itemView.findViewById(R.id.password)
            val email: TextView = itemView.findViewById(R.id.email)
            name.text = data.name
            password.text = data.password
            email.text = data.email

            //set the onclick listener for the singlt list item
            itemView.setOnClickListener({
                Log.e("ItemClicked", "$name Clicked ")
            })
        }

    }
}