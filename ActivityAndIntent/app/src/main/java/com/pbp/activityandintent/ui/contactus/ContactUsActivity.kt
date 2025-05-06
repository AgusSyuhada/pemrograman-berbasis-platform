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
        val layoutLinkedin = findViewById<LinearLayout>(R.id.layoutLinkedin)
        val layoutEmail = findViewById<LinearLayout>(R.id.layoutEmail)
        val layoutInstagram = findViewById<LinearLayout>(R.id.layoutInstagram)
        val layoutGithub = findViewById<LinearLayout>(R.id.layoutGithub)

//        val tvAddress = findViewById<TextView>(R.id.tvAddress)
//        val tvWebsite = findViewById<TextView>(R.id.tvWebsite)
//        val tvPhone = findViewById<TextView>(R.id.tvPhone)
//        val tvLinkedin = findViewById<TextView>(R.id.tvLinkedin)
//        val tvEmail = findViewById<TextView>(R.id.tvEmail)
//        val tvInstagram = findViewById<TextView>(R.id.tvInstagram)
//        val tvGithub = findViewById<TextView>(R.id.tvGithub)

        layoutLocation.setOnClickListener {
            try {
                val address = getString(R.string.address)
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
                val websiteUrl = getString(R.string.website_link)
                val fullUrl = if (!websiteUrl.startsWith("http://") && !websiteUrl.startsWith("https://")) {
                    "https://$websiteUrl"
                } else {
                    websiteUrl
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                startActivity(browserIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.browser_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutPhone.setOnClickListener {
            try {
                val phoneNumber = getString(R.string.phone_number)
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(dialIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.dialer_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutLinkedin.setOnClickListener {
            try {
                val linkedinId = getString(R.string.linkedin_id)
                val fullUrl = "https://linkedin.com/in/$linkedinId"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                startActivity(browserIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.browser_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutEmail.setOnClickListener {
            try {
                val emailAddress = getString(R.string.email_address)
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$emailAddress")
                }
                startActivity(emailIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Cannot open email app", Toast.LENGTH_SHORT).show()
            }
        }

        layoutInstagram.setOnClickListener {
            try {
                val instagramId = getString(R.string.instagram_id)
                val instagramIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("http://instagram.com/_u/$instagramId")
                    setPackage("com.instagram.android")
                }

                if (instagramIntent.resolveActivity(packageManager) != null) {
                    startActivity(instagramIntent)
                } else {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/$instagramId"))
                    startActivity(browserIntent)
                }
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.browser_error), Toast.LENGTH_SHORT).show()
            }
        }

        layoutGithub.setOnClickListener {
            try {
                val githubId = getString(R.string.github_id)
                val fullUrl = "https://github.com/$githubId"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                startActivity(browserIntent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.browser_error), Toast.LENGTH_SHORT).show()
            }
        }
    }
}