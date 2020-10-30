package com.example.sciencefairapplication1final
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main8.*

class MainActivity8 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)
        auth = FirebaseAuth.getInstance()
        btn_confirm_password.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword(){

        if (previouspassword.toString().isNotEmpty() &&
            newpassword.toString().isNotEmpty() &&
            confirmpassword.toString().isNotEmpty())

            {

            if (newpassword.text.toString().equals(confirmpassword.text.toString())) {

                val user = auth.currentUser
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, previouspassword.text.toString())

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Re-Authentication success.", Toast.LENGTH_SHORT).show()
                                user.updatePassword(newpassword.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show()
                                            auth.signOut()
                                            startActivity(Intent(this, MainActivity3::class.java))
                                            finish()
                                        }
                                    }

                            } else {
                                Toast.makeText(this, "Re-Authentication failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    startActivity(Intent(this, MainActivity3::class.java))
                    finish()
                }

            } else {
                Toast.makeText(this, "Password mismatching.", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Please enter all the fields.", Toast.LENGTH_SHORT).show()
        }

    }
}