package com.example.sciencefairapplication1final
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)

            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)

        }
        sign_out_button.setOnClickListener {
            val sign_out = Firebase.auth.signOut()
            sign_out
            Toast.makeText(
                baseContext, "You have signed out of your account.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}



