package com.pbp.activityandintent.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pbp.activityandintent.R
import com.pbp.activityandintent.ui.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private val validUsername = "admin"
    private val validPassword = "password123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    etUsername.error = getString(R.string.username_empty)
                    etUsername.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = getString(R.string.password_empty)
                    etPassword.requestFocus()
                }
                else -> {
                    if (isValidCredentials(username, password)) {
                        Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("USERNAME", username)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == validUsername && password == validPassword
    }
}