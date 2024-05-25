package com.example.vehiclehealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StatusCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val register: Button = findViewById(R.id.register)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_status_check)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        register.setOnClickListener {
            // Handle Log Out action
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}