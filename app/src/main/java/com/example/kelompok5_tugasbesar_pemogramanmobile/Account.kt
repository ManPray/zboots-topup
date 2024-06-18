package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val buttonCart = findViewById<ImageButton>(R.id.cart_button_in_account)
        val buttonHome = findViewById<ImageButton>(R.id.home_button_in_account)
        val btnLogout = findViewById<Button>(R.id.logout_button)
        val tampilName = findViewById<TextView>(R.id.tampil_name_halaman_account)
        val tampilEmail = findViewById<TextView>(R.id.tampil_email_halaman_account)
        val edit_Profile = findViewById<Button>(R.id.edit_profile)
        val helpSupport = findViewById<Button>(R.id.help_and_support)

        if (currentUser != null) {
            tampilName.text = currentUser.displayName
            tampilEmail.text = currentUser.email
        }

        edit_Profile.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        helpSupport.setOnClickListener {
            val intent = Intent(this, HelpSupport::class.java)
            startActivity(intent)
        }

        buttonCart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        buttonHome.setOnClickListener {
            val intent = Intent (this, Home::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            logout()
        }

        val switchNotifications = findViewById<Switch>(R.id.switch_notifications)
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notification is ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notification is OFF", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun logout() {
        auth.signOut()
        val intent = Intent(this, LoginAtauSignup::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}