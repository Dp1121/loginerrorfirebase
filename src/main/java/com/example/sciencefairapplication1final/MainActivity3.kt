package com.example.sciencefairapplication1final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()


        button6.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }


        button5.setOnClickListener {
            doLogin()
        }


        button7.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.activity_main4,null)
            val username = view.findViewById<EditText>(R.id.email_password_reset)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(username)
            })
            builder.setNegativeButton("close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }


    }


    private fun forgotPassword(username : EditText){
        if (username.text.toString().isEmpty()) {
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return
        }


        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent.",Toast.LENGTH_SHORT).show()
                }
            }


    }


    private fun doLogin() {
        if (tv_emailL.text.toString().isEmpty()) {
            tv_emailL.error = "Please enter email"
            tv_emailL.requestFocus()
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(tv_emailL.text.toString()).matches()) {
            tv_emailL.error = "Please enter valid email"
            tv_emailL.requestFocus()
            return
        }


        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return
        }


        auth.signInWithEmailAndPassword(tv_emailL.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }


    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    private fun updateUI(currentUser: FirebaseUser?) {


        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, MapsActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}