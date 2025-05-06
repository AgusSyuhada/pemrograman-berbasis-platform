package com.pbp.activityandintent.ui.contactus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.pbp.activityandintent.R

class ContactUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        val layoutLocation = findViewById<LinearLayout>(R.id.layoutLocation)
        val layoutWebsite = findViewById<LinearLayout>(R.id.layoutWebsite)
        val layoutPhone = findViewById<LinearLayout>(R.id.layoutPhone)
        val layoutHours = findViewById<LinearLayout>(R.id.layoutHours)

        val tvAddress = findViewById<TextView>(R.id.tvAddress)
        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)

        layoutLocation.setOnClickListener {
            try {
                val address = tvAddress.text.toString()
                val encodedAddress = Uri.encode(address)
                val gmmIntentUri = Uri.parse("geo:0,0?q=$encodedAddress")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    val mapUrl = "https://maps.google.com/?q=$encodedAddress"
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                    startActivity(browserIntent)
                }
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.map_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutWebsite.setOnClickListener {
            try {
                val websiteUrl = tvWebsite.text.toString()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
                startActivity(browserIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.browser_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutPhone.setOnClickListener {
            try {
                val phoneNumber = tvPhone.text.toString()
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(dialIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.dialer_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutHours.setOnClickListener {
            val hoursInfo = getString(R.string.operating_hours_desc)
            Toast.makeText(this, hoursInfo, Toast.LENGTH_LONG).show()
        }
    }
}