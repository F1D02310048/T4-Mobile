package com.example.studentcontactapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHome = findViewById<TextView>(R.id.btnHome)
        val btnProfile = findViewById<TextView>(R.id.btnProfile)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, HomeFragment())
            .commit()

        btnHome.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, HomeFragment())
                .commit()
        }

        btnProfile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, ProfileFragment())
                .commit()
        }
    }
}