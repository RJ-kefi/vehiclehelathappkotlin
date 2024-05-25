package com.example.vehiclehealth


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val actionButton1: Button = findViewById(R.id.actionButton1)
        val actionButton2: Button = findViewById(R.id.actionButton2)
        val actionButton4: Button = findViewById(R.id.actionButton4)

        actionButton1.setOnClickListener {
            // Handle View Profile action
        }

        actionButton2.setOnClickListener {
            // Handle Settings action
        }

        actionButton4.setOnClickListener {
            // Handle Log Out action
            val intent = Intent(this, StatusCheckActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}
