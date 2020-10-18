package com.example.sciencefairapplication1final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity4 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val button = findViewById<Button>(R.id.button)
        val button1 = findViewById<Button>(R.id.button1)


        button.setOnClickListener {
        }


                button1.setOnClickListener {
                    val intent = Intent(this, MainActivity3::class.java)

                    startActivity(intent)
                }
            }
        }

