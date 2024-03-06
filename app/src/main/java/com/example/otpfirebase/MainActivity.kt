package com.example.otpfirebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var phone: EditText
    private lateinit var otp: EditText
    private lateinit var sendbtn: Button
    private lateinit var verifybtn: Button

    private lateinit var auth: FirebaseAuth

    var verificationID = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone = findViewById(R.id.Phoneeditext)
        otp = findViewById(R.id.otpedittext)
        sendbtn = findViewById(R.id.sendotp)
        verifybtn = findViewById(R.id.verfiyotp)

        auth = FirebaseAuth.getInstance()

        sendbtn.setOnClickListener {
            sendotp()
        }
        verifybtn.setOnClickListener {
            verifyotp()
        }
    }

    private fun sendotp() {

        val phoneAuthOptions = PhoneAuthOptions.newBuilder()
            .setPhoneNumber("+91${phone.text}")
            .setActivity(this)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Toast.makeText(this@MainActivity, "Verification Success", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(
                        this@MainActivity, "Verification Failed ${p0.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(
                    verificationId: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, p1)

                    verificationID = verificationId
                    Toast.makeText(this@MainActivity, "OTP SEND", Toast.LENGTH_SHORT).show()
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)

    }

    private fun verifyotp() {
        val otptext = otp.text.toString()
        val pnoneAuthCredential = PhoneAuthProvider.getCredential(verificationID, otptext)

        auth.signInWithCredential(pnoneAuthCredential)
            .addOnSuccessListener {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

}