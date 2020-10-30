package com.example.sciencefairapplication1final
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        auth = FirebaseAuth.getInstance()

        button6.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

            button5.setOnClickListener {
                doLogin()
            }
        }

        button7.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
            finish()
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
                startActivity(Intent(this, MapsActivity::class.java))
                finish()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}