package com.example.projectharmonymusicapp.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.providers.ProviderType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private lateinit var buttonToSignUp: Button
    private lateinit var editTextSignUpUsername: EditText
    private lateinit var editTextSignUpEmail: EditText
    private lateinit var editTextSignUpPassword: EditText
    private lateinit var imageViewSignUpFacebook: ImageView
    private lateinit var imageViewSignUpGoogle: ImageView
    private lateinit var imageViewSignUpApple: ImageView

    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
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
        imageViewSignUpGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()
            signInWithGoogle()

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

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w("Auth", "Google sign in failed", e)
                Toast.makeText(this, "Error de autenticación con Google.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión con Google exitoso
                    Log.d("Auth", "signInWithCredential:success")
                    Toast.makeText(baseContext, "Autenticación con Google exitosa.", Toast.LENGTH_SHORT).show()
                    // Navegar a la página principal
                    val intent = Intent(this, NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Error en el inicio de sesión con Google
                    Log.w("Auth", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Error de autenticación con Google.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
