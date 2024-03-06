package com.example.otpfirebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso

class photo_upload : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var uploagImage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_upload)

        imageView = findViewById(R.id.imageView)
        uploagImage = findViewById(R.id.uploagimage)

        enableEdgeToEdge()

        uploagImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            imageLauncher.launch(intent)
        }
    }

    val imageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data != null) {
                    val ref = com.google.firebase.ktx.Firebase.storage.reference.child("Photo")
                    ref.putFile(it.data!!.data!!)
                        .addOnSuccessListener {
                            ref.downloadUrl.addOnSuccessListener { uri ->
                                Toast.makeText(this, "Photo Upload", Toast.LENGTH_SHORT).show()

                                Picasso.get().load(uri.toString()).into(imageView)
                            }
                        }
                }
            }
        }}