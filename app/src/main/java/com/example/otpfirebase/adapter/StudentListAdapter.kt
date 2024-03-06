package com.example.otpfirebase.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.otpfirebase.R
import com.example.otpfirebase.models.StudentModel

class StudentListAdapter(private val students: MutableList<StudentModel>, private val context: Context) : BaseAdapter(){

    override fun getCount(): Int {
        return  students.size
    }

    override fun getItem(position: Int): Any {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
       return  position.toLong()
    }

    @SuppressLint("ViewHolder", "MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout. students_list_item, parent, false)
        val name = view.findViewById<TextView>(R.id.name_text)
        val id = view.findViewById<TextView>(R.id.id_text)

        name.text = students[position].name.toString()
        id.text = students[position].id.toString()
        return  view

    }
}