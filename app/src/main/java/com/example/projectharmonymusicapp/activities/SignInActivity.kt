package com.example.projectharmonymusicapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectharmonymusicapp.R

class SignInActivity : AppCompatActivity() {

    private lateinit var buttonToSignInn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        buttonToSignInn = findViewById(R.id.button_to_sign_in)
        buttonToSignInn.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }
}