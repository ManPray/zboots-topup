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
import com.google.firebase.auth.UserProfileChangeRequest

class Signup : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        val emailSignup = findViewById<EditText>(R.id.email_signup)
        val nameSignup = findViewById<EditText>(R.id.name_signup)
        val passwordSignup = findViewById<EditText>(R.id.password_signup)
        val confirmPasswordSignup = findViewById<EditText>(R.id.confirmpassword_signup)
        val buttonSignup = findViewById<Button>(R.id.button_signup_halaman_signup)
        val backToSignAtauLogin = findViewById<Button>(R.id.back_from_signup)
        val haveAccount = findViewById<Button>(R.id.have_account)

        backToSignAtauLogin.setOnClickListener {
            val intent = Intent(this, LoginAtauSignup::class.java)
            startActivity(intent)
        }

        haveAccount.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        buttonSignup.setOnClickListener {
            val email = emailSignup.text.toString().trim()
            val name = nameSignup.text.toString().trim()
            val password = passwordSignup.text.toString().trim()
            val confirmPassword = confirmPasswordSignup.text.toString().trim()

            // fungsi if, jika kolom kosong maka kode di dalam blok setOnClickListener akan berhenti dieksekusi
            // mencegah aplikasi mencoba signup tanpa kolom kosong
            if (TextUtils.isEmpty(email)) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(name)) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(confirmPassword)) {
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                return@setOnClickListener
            }

            // createUserWithEmailAndPassword() untuk membuat email dan password baru
            // addOnCompleteListener() untuk menangani hasil operasi pendaftaran
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user =
                            mAuth.currentUser // mengembalikan data signup yang baru terdaftar
                        val profileUpdates =
                            UserProfileChangeRequest.Builder().setDisplayName(name).build()
                        // menjalankan permintaan untuk memperbarui data signup
                        user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                                Handler(Looper.getMainLooper()).postDelayed({
                                    val intent = Intent(this, Login::class.java)
                                    startActivity(intent)
                                    finish()
                                }, 1000) // 5000 milliseconds = 5 seconds
                            }
                        }
                    } else {
                        // menampilkan pesan gagal, jika data signup sudah terdaftar
                        Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                    }
                }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}