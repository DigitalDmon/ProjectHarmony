package com.example.projectharmonymusicapp.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.providers.ProviderType
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var buttonToSignUp: Button
    private lateinit var editTextSignUpUsername: EditText
    private lateinit var editTextSignUpEmail: EditText
    private lateinit var editTextSignUpPassword: EditText
    private lateinit var imageViewSignUpFacebook: ImageView
    private lateinit var imageViewSignUpGoogle: ImageView
    private lateinit var imageViewSignUpApple: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        buttonToSignUp = findViewById(R.id.button_to_sign_up)
        editTextSignUpUsername = findViewById(R.id.edit_text_sign_up_username)
        editTextSignUpEmail = findViewById(R.id.edit_text_sign_up_email)
        editTextSignUpPassword = findViewById(R.id.edit_text_sign_up_password)
        imageViewSignUpFacebook = findViewById(R.id.image_view_sign_up_facebook)
        imageViewSignUpGoogle = findViewById(R.id.image_view_sign_up_google)
        imageViewSignUpApple = findViewById(R.id.image_view_sign_up_apple)

        buttonToSignUp.setOnClickListener {
            if (editTextSignUpEmail.text.isNotEmpty() && editTextSignUpPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextSignUpEmail.text.toString(), editTextSignUpPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showNavigationActivity(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            } else {
                showFillFieldsMessage()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El email ya existe")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun showNavigationActivity(email: String, provider: ProviderType) {
        val intent = Intent(this, NavigationActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }

    private fun showFillFieldsMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Campos incompletos")
        builder.setMessage("Por favor, llena ambos campos de email y contraseña.")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }



}
