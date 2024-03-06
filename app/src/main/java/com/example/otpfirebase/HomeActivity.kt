package com.example.otpfirebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.otpfirebase.adapter.StudentListAdapter
import com.example.otpfirebase.models.StudentModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var studentsListView: ListView
    private val db = FirebaseFirestore.getInstance()

    private lateinit var addButton:FloatingActionButton

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

      studentsListView = findViewById(R.id.studentsListView)
      addButton = findViewById(R.id.addButton)

      val data =  getStudentList()

      studentsListView.adapter = StudentListAdapter(data, this)


        addButton.setOnClickListener {

            val hasData = HashMap<String, Any>()

            hasData["id"] = "dhjkvhdbsdjdfhbksdfsdfshjdgksdlhkb"
            hasData["name"] = "dhjkvhdbdfdsgsdjdfhbkhjdgksdlhkb"
            hasData["email"] = "sda@gmail.com"

            addStudent(hasData)
        }
    }

    fun getStudentList(): ArrayList<StudentModel> {
        val studentList= ArrayList<StudentModel>()
        db.collection("students").get()
            .addOnSuccessListener {
                val result = it.toObjects(StudentModel::class.java)
                studentList.addAll(result)
                studentsListView.adapter = StudentListAdapter(result, this)

            }
            .addOnFailureListener {

            }

        return studentList
    }
    fun getStudent(id:String){
        db.collection("students").document(id).get()
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    fun addStudent(data: HashMap<String, Any>){
        db.collection("students").add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Data adding completed", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Data adding failed", Toast.LENGTH_SHORT).show()

            }
    }

    fun setStudent(data: HashMap<String, Any>, id:String){

        db.collection("students").document(id).set(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Data setting completed", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data setting failed", Toast.LENGTH_SHORT).show()

            }
    }

    fun updateStudent(data: HashMap<String, Any>, id:String){

        db.collection("students").document(id).update(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Data setting completed", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data setting failed", Toast.LENGTH_SHORT).show()

            }
    }

    fun deleteStudent(id:String){

        db.collection("students").document(id).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Data setting completed", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data setting failed", Toast.LENGTH_SHORT).show()

            }
    }
}