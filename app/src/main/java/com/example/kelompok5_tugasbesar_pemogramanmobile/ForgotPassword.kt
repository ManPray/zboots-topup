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

class ForgotPassword : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        mAuth = FirebaseAuth.getInstance()

        val buttonBackToLogin = findViewById<Button>(R.id.emailchangepassword_back_to_login)
        val emailChangePassword = findViewById<EditText>(R.id.email_forgot_password)
        val buttonSendEmailChangePassword = findViewById<Button>(R.id.send_email_changepassword)

        buttonBackToLogin.setOnClickListener{
            val intent = Intent (this, Login::class.java)
            startActivity(intent)
        }

        buttonSendEmailChangePassword.setOnClickListener {
            val email = emailChangePassword.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                return@setOnClickListener
            }

            // sendPasswordResetEmail() untuk mengirim email kepada pengguna dalam bentuk link untuk mereset kata sandi
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Email has benn sent", Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                }else{
                    Toast.makeText(this, "Email failed to send", Toast.LENGTH_SHORT).show()
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