package com.example.sciencefairapplication1final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button10 = findViewById<Button>(R.id.button10)


        button7.setOnClickListener {
            val intent = Intent( this, MainActivity5::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right)
        }

        button8.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right)
        }


        button10.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)

            startActivity(intent)

        }
    }



}