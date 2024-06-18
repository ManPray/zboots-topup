package com.example.kelompok5_tugasbesar_pemogramanmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PilihItem : AppCompatActivity() {

    private val cartItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_item)

        val allItem = findViewById<LinearLayout>(R.id.all_item)
        val buttonCart = findViewById<ImageButton>(R.id.cart_button_in_pilihitem)
        val buttonAccount = findViewById<ImageButton>(R.id.account_button_in_pilihitem)
        val buttonHome = findViewById<ImageButton>(R.id.home_button_in_pilihitem)

        buttonCart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        buttonAccount.setOnClickListener {
            val intent = Intent (this, Account::class.java)
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