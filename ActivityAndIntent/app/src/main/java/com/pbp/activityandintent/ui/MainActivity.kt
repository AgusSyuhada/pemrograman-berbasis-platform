package com.pbp.activityandintent.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pbp.activityandintent.R
import com.pbp.activityandintent.ui.contactus.ContactUsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnContactUs: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContactUs = findViewById(R.id.btnContactUs)

        btnContactUs.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
    }
}