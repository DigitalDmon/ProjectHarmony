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
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var oneTapClient: SignInClient? = null
    private lateinit var signInRequest: BeginSignInRequest

    private lateinit var buttonToSignIn: Button
    private lateinit var editTextSignInEmail: EditText
    private lateinit var editTextSignInPassword: EditText
    private lateinit var imageViewSignInFacebook: ImageView
    private lateinit var imageViewSignInGoogle: ImageView
    private lateinit var imageViewSignInApple: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        oneTapClient = Identity.getSignInClient(this)
        setContentView(R.layout.activity_sign_in)
        editTextSignInEmail = findViewById(R.id.edit_text_sign_in_email)
        editTextSignInPassword = findViewById(R.id.edit_text_sign_in_password)
        buttonToSignIn = findViewById(R.id.button_to_sign_in)
        imageViewSignInFacebook = findViewById(R.id.image_view_sign_in_facebook)
        imageViewSignInGoogle = findViewById(R.id.image_view_sign_in_google)
        imageViewSignInApple = findViewById(R.id.image_view_sign_in_apple)
        buttonToSignIn.setOnClickListener {
            if (editTextSignInEmail.text.isNotEmpty() && editTextSignInPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextSignInEmail.text.toString(), editTextSignInPassword.text.toString()).addOnCompleteListener {
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
        imageViewSignInGoogle.setOnClickListener {
            signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true).setServerClientId(getString(R.string.default_web_client_id)).setFilterByAuthorizedAccounts(false).build()).build()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null) run {
//            val intent = Intent(this, NavigationActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar el correo y la contraseña")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    fun showNavigationActivity(email: String, provider: ProviderType) {
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
