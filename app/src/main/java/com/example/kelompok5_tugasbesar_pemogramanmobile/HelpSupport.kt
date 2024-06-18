package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HelpSupport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_help_support)

        val kembaliKeEditProfile = findViewById<Button>(R.id.back_to_edit_profile)
        val buttonCart = findViewById<ImageButton>(R.id.cart_button_in_helpsupport)
        val buttonHome = findViewById<ImageButton>(R.id.home_button_in_helpsupport)
        val buttonContactus = findViewById<Button>(R.id.contact_us)
        val tombolPrivacyPolicy = findViewById<Button>(R.id.button_privacy_policy)

        buttonContactus.setOnClickListener {
            val intent = Intent(this, ContactUs::class.java)
            startActivity(intent)
        }

        tombolPrivacyPolicy.setOnClickListener {
            val intent = Intent(this, PrivacyPolicy::class.java)
            startActivity(intent)
        }

        kembaliKeEditProfile.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        buttonCart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        buttonHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}