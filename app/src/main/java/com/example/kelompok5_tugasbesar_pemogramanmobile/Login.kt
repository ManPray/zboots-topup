package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val forgotPassword = findViewById<Button>(R.id.forgot_password)
        val backToLoginAtauSignup = findViewById<Button>(R.id.back_from_login)
        val emailLogin = findViewById<EditText>(R.id.email_login)
        val passwordLogin = findViewById<EditText>(R.id.password_login)
        val buttonLogin = findViewById<Button>(R.id.button_login_halaman_login)

        forgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        backToLoginAtauSignup.setOnClickListener {
            val intent = Intent(this, LoginAtauSignup::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val email = emailLogin.text.toString().trim()
            val password = passwordLogin.text.toString().trim()

            // fungsi if, jika kolom kosong maka kode di dalam blok setOnClickListener akan berhenti dieksekusi
            // mencegah aplikasi mencoba signup tanpa kolom kosong
            if (TextUtils.isEmpty(email)) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                return@setOnClickListener
            }

            // createUserWithEmailAndPassword() untuk melakukan proses autentikasi menggunakan Firebase Authentication
            // addOnCompleteListener() untuk menangani hasil operasi login
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                } else {
                    Toast.makeText(this, "Email or password does not match", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}