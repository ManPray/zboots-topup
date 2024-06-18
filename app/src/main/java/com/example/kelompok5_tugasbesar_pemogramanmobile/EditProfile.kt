package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class EditProfile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val buttonCart = findViewById<ImageButton>(R.id.cart_button_in_edit_profile)
        val buttonHome = findViewById<ImageButton>(R.id.home_button_in_edit_profile)
        val tampilEmail = findViewById<TextView>(R.id.view_email)
        val saveChanges = findViewById<Button>(R.id.save_changes)
        val kembaliKeAccount = findViewById<Button>(R.id.back_to_account)

        if (currentUser != null) {
            tampilEmail.text = currentUser.email
        }

        kembaliKeAccount.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        buttonCart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        saveChanges.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        buttonHome.setOnClickListener {
            val intent = Intent (this, Home::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}