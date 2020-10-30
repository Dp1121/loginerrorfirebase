package com.example.sciencefairapplication1final
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = FirebaseAuth.getInstance()
        button4.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
            finish()
        }
        button3.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (tv_email.text.toString().isEmpty()) {
            tv_email.error = "Please enter email"
            tv_email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_email.text.toString()).matches()) {
            tv_email.error = "Please enter valid email"
            tv_email.requestFocus()
            return
        }

        if (enteryourdetailsnow.text.toString().isEmpty()) {
            enteryourdetailsnow.error = "Please enter password"
            enteryourdetailsnow.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(
            tv_email.text.toString(),
            enteryourdetailsnow.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext, "You Have Registered Successfully", Toast.LENGTH_SHORT
                    ).show()
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener {task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, MainActivity3::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()                }
            }

    }
}
