package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_us)

        val kembaliKeHelpSupport = findViewById<Button>(R.id.back_to_helpsupport_from_contact_us)
        val buttonCart = findViewById<ImageButton>(R.id.cart_button_in_contactus)
        val buttonHome = findViewById<ImageButton>(R.id.home_button_in_contactus)
        val buttonSend = findViewById<Button>(R.id.send)

        buttonSend.setOnClickListener {
            val intent = Intent(this, HelpSupport::class.java)
            startActivity(intent)
        }

        kembaliKeHelpSupport.setOnClickListener {
            val intent = Intent(this, HelpSupport::class.java)
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