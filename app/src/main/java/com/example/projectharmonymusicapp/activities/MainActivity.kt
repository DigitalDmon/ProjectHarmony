package com.example.projectharmonymusicapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectharmonymusicapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSignIn: Button
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSignIn = findViewById(R.id.button_sign_in)
        buttonSignUp = findViewById(R.id.button_sing_up)
        buttonSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}