package com.example.sciencefairapplication1final
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
                val button = findViewById<Button>(R.id.button)
                val button2 = findViewById<Button>(R.id.button2)
                val button4 = findViewById<Button>(R.id.button4)

                button2.setOnClickListener {
                    val intent = Intent(this, MainActivity3::class.java)

                    startActivity(intent)
                }

                button.setOnClickListener {
                    val intent = Intent(this, MainActivity2::class.java)

                    startActivity(intent)

                }
                }

            }



