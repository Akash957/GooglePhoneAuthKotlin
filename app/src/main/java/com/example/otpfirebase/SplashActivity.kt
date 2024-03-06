package com.example.otpfirebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val auth= FirebaseAuth.getInstance()
        if (auth.currentUser !=null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}